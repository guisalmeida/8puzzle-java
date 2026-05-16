package com.guisalmeida.eightpuzzle.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PlayerDAO {

	private final EntityManager entityManager;

	public PlayerDAO() {
		this(PersistenceManager.getEntityManager());
	}

	public PlayerDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Player player) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(player);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public void update(Player player) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(player);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Player findById(Integer id) {
		return entityManager.find(Player.class, id);
	}

	public void close() {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
