package br.ies.aps.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Board board;

	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void shouldBeRightPointerPosition() {
		assertEquals(0, board.getPointer().getValue());
	}
}
