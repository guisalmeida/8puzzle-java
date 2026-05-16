package com.guisalmeida.eightpuzzle.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PersistenceManager {

	private static EntityManagerFactory emf;

	private PersistenceManager() {
	}

	public static synchronized EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			Map<String, String> overrides = new HashMap<>();
			Properties props = loadProperties();

			String dbUrl = resolve("DB_URL", "db_url", props);
			String dbUser = resolve("DB_USER", "db_username", props);
			String dbPassword = resolve("DB_PASSWORD", "db_password", props);

			if (dbUrl != null) {
				overrides.put("javax.persistence.jdbc.url", dbUrl);
			}
			if (dbUser != null) {
				overrides.put("javax.persistence.jdbc.user", dbUser);
			}
			if (dbPassword != null) {
				overrides.put("javax.persistence.jdbc.password", dbPassword);
			}

			emf = Persistence.createEntityManagerFactory("eight-puzzle-pu", overrides);
		}
		return emf;
	}

	public static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	public static synchronized void close() {
		if (emf != null && emf.isOpen()) {
			emf.close();
			emf = null;
		}
	}

	/**
	 * Resolves a config value: environment variable takes priority,
	 * then falls back to application.properties.
	 */
	private static String resolve(String envKey, String propKey, Properties props) {
		String envValue = System.getenv(envKey);
		if (envValue != null && !envValue.isEmpty()) {
			return envValue;
		}
		String propValue = props.getProperty(propKey);
		if (propValue != null && !propValue.isEmpty()) {
			return propValue;
		}
		return null;
	}

	private static Properties loadProperties() {
		Properties props = new Properties();
		try (InputStream input = PersistenceManager.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			if (input != null) {
				props.load(input);
			}
		} catch (IOException e) {
			// Silently fall back to persistence.xml defaults
		}
		return props;
	}
}
