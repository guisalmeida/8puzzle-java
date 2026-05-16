package com.guisalmeida.eightpuzzle.model;

import javax.persistence.EntityManager;

/**
 * Quick smoke test to verify the Supabase connection works.
 * Run with: mvn exec:java -Dexec.mainClass="com.guisalmeida.eightpuzzle.model.ConnectionTest"
 */
public class ConnectionTest {

	public static void main(String[] args) {
		System.out.println("Connecting to Supabase...");
		try {
			EntityManager em = PersistenceManager.getEntityManager();
			System.out.println("✅ Connection successful!");

			// Try a simple query
			Object result = em.createNativeQuery("SELECT 1").getSingleResult();
			System.out.println("✅ Query executed: SELECT 1 = " + result);

			em.close();
			PersistenceManager.close();
			System.out.println("✅ Connection closed.");
		} catch (Exception e) {
			System.out.println("❌ Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

