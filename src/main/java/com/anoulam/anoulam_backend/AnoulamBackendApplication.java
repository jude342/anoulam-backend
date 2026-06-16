package com.anoulam.anoulam_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnoulamBackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		setSystemProperties("DB_URL", dotenv);
		setSystemProperties("DB_USERNAME", dotenv);
		setSystemProperties("DB_PASSWORD", dotenv);
		setSystemProperties("ANOULAM_TOKEN_KEY", dotenv);

		SpringApplication.run(AnoulamBackendApplication.class, args);
	}

	private static void setSystemProperties(String key, Dotenv dotenv) {
		String value = dotenv.get(key);

		if (value == null || value.isEmpty()) {
			value = System.getenv(key);
		}

		if (value != null && !value.isEmpty()) {
			System.setProperty(key, value);
		}
	}
}
