package com.guisalmeida.eightpuzzle.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SaveNewGameDAO {
	private final Board board;
	private final Player player;
	private final EntityManager entityManager;

	public SaveNewGameDAO(Board board, Player player) {
		this(board, player, PersistenceManager.getEntityManager());
	}

	public SaveNewGameDAO(Board board, Player player, EntityManager entityManager) {
		this.board = board;
		this.player = player;
		this.entityManager = entityManager;
	}

	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			entityManager.persist(board);
			player.setBoard(board);
			entityManager.persist(player);

			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Integer getBoardId() {
		return board.getId();
	}
	
	public Integer getPlayerId() {
		return player.getId();
	}
}
