package com.anoulam.anoulam_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Value("${app.security.token}")
    private String secretToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String incomingToken = request.getHeader("X-AnoUlam-Token");

        if (incomingToken != null && incomingToken.equals(secretToken)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("ACCESS DENIED");
        return false;
    }
}