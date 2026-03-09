package com.guisalmeida.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
	private Board board;

	@BeforeEach
	public void setup() {
		board = new Board();
	}

	@Test
	public void shouldBeRightPointerPosition() {
		assertEquals(0, board.getPointer().getValue());
	}
}
