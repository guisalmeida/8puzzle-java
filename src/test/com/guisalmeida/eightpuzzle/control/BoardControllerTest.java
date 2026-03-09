package com.guisalmeida.eightpuzzle.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.guisalmeida.eightpuzzle.model.Board;
import org.junit.Before;
import org.junit.Test;

public class BoardControllerTest {
	private BoardController boardController;
	
	@Before
	public void setup() {
		boardController = new BoardController(new Board());
	}

	@Test
	public void shouldMovePointerUp() {
		Integer positionAbove = boardController.getBoard().getPointer().getUp().getValue();
		boardController.moveUp();
		assertEquals(positionAbove, boardController.getBoard().getPointer().getDown().getValue());
	}
	
	@Test
	public void shouldMovePointerLeft() {
		Integer positionLeft = boardController.getBoard().getPointer().getLeft().getValue();
		boardController.moveLeft();
		assertEquals(positionLeft, boardController.getBoard().getPointer().getRight().getValue());
	}
	
	@Test
	public void shouldMovePointerRight() {
		Integer positionRight = boardController.getBoard().getPointer().getRight().getValue();
		boardController.moveRight();
		assertEquals(positionRight, boardController.getBoard().getPointer().getLeft().getValue());
	}

	@Test
	public void shouldMovePointerDown() {
		Integer positionBelow = boardController.getBoard().getPointer().getDown().getValue();
		boardController.moveDown();
		assertEquals(positionBelow, boardController.getBoard().getPointer().getUp().getValue());
	}
}
