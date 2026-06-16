
package com.anoulam.anoulam_backend.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_MINUTE = 25;
    private static final long ONE_MINUTE_IN_MS = 60000;
    private final Map<String, UserRequestWindow> ipTracker = new ConcurrentHashMap<>();

    private static class UserRequestWindow {
        long startTime = System.currentTimeMillis();
        int requestCount = 0;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }

        long now = System.currentTimeMillis();
        UserRequestWindow window = ipTracker.computeIfAbsent(clientIp, k -> new UserRequestWindow());

        synchronized (window) {
            if (now - window.startTime > ONE_MINUTE_IN_MS) {
                window.startTime = now;
                window.requestCount = 0;
            }
            window.requestCount++;

            if (window.requestCount > MAX_REQUESTS_PER_MINUTE) {
                response.setStatus(429);
                response.setContentType("application/json");
                response.getWriter().write("""
                        {
                            "error": "Too many requests",
                            "message": "Please wait a minute before trying again."
                        }
                        """);
                return false;
            }
        }

        return true;
    }
}