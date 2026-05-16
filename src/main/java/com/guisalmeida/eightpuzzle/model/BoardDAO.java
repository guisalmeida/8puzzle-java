package com.guisalmeida.eightpuzzle.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BoardDAO {

	private final EntityManager entityManager;

	public BoardDAO() {
		this(PersistenceManager.getEntityManager());
	}

	public BoardDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Board board) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(board);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public void update(Board board) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(board);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Board findById(Integer id) {
		return entityManager.find(Board.class, id);
	}

	public void close() {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
