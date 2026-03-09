package com.guisalmeida.eightpuzzle.model;

public class Tile {
	private Integer value;

	private Tile up;
	private Tile down;
	private Tile left;
	private Tile right;

	private Board board;

	public Tile(Integer value, Board board) {
		this.setValue(value);
		this.up = this;
		this.down = this;
		this.left = this;
		this.right = this;
		this.board = board;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void swapValue(Tile source, Tile destination) {
		Integer temp = source.getValue();
		source.setValue(destination.getValue());
		destination.setValue(temp);
	}

	public Tile getUp() {
		return up;
	}

	public void setUp(Tile up) {
		this.up = up;
	}

	public Tile getDown() {
		return down;
	}

	public void setDown(Tile down) {
		this.down = down;
	}

	public Tile getLeft() {
		return left;
	}

	public void setLeft(Tile left) {
		this.left = left;
	}

	public Tile getRight() {
		return right;
	}

	public void setRight(Tile right) {
		this.right = right;
	}

	public void moveUp() {
		swapValue(this, up);
		this.board.notifyObservers(this.board);
	}

	public void moveDown() {
		swapValue(this, down);
		this.board.notifyObservers(this.board);
	}

	public void moveLeft() {
		swapValue(this, left);
		this.board.notifyObservers(this.board);
	}

	public void moveRight() {
		swapValue(this, right);
		this.board.notifyObservers(this.board);
	}
}
