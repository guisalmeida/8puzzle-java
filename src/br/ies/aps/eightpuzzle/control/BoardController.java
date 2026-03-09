package br.ies.aps.eightpuzzle.control;

import br.ies.aps.eightpuzzle.model.Board;

public class BoardController {
	private Board board;

	public BoardController(Board board) {
		this.setBoard(board);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void moveUp() {
		board.getPointer().moveUp();
		board.setPointer(board.getPointer().getUp());
	}

	public void moveDown() {
		board.getPointer().moveDown();
		board.setPointer(board.getPointer().getDown());
	}

	public void moveLeft() {
		board.getPointer().moveLeft();
		board.setPointer(board.getPointer().getLeft());
	}

	public void moveRight() {
		board.getPointer().moveRight();
		board.setPointer(board.getPointer().getRight());
	}
}
