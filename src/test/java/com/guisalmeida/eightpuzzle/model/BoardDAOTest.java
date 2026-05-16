package com.guisalmeida.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BoardDAOTest {
	private static EntityManagerFactory emf;

	@BeforeAll
	public static void setupFactory() {
		emf = Persistence.createEntityManagerFactory("eight-puzzle-pu");
	}

	@AfterAll
	public static void closeFactory() {
		if (emf != null) {
			emf.close();
		}
	}

	@Test
	public void shouldInsertNewBoard() {
		EntityManager em = emf.createEntityManager();
		BoardDAO boardDAO = new BoardDAO(em);
		Board board = new Board();

		boardDAO.insert(board);

		assertNotNull(board.getId());
		em.close();
	}
}
