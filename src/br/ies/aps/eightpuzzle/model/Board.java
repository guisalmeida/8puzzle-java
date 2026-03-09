package br.ies.aps.eightpuzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Tile pointer;
	private Tile tileCenter;
	private Tile tileCenterRight;
	private Tile tileCenterLeft;
	private Tile tileBottomCenter;
	private Tile tileBottomRight;
	private Tile tileBottomLeft;
	private Tile tileTopCenter;
	private Tile tileTopRight;
	private Tile tileTopLeft;

	private Integer id;

	private final List<BoardObserver> observers = new ArrayList<>();

	public Board() {
		generateTiles();
	}

	public void registerObserver(BoardObserver observer) {
		observers.add(observer);
	}

	public void notifyObservers(Board board) {
		for (BoardObserver observer : observers) {
			observer.onBoardStateChanged(this);
		}
	}

	public Tile getPointer() {
		return pointer;
	}

	public void setPointer(Tile pointer) {
		this.pointer = pointer;
	}

	public Integer getTileCenter() {
		return tileCenter.getValue();
	}
	
	public void setTileCenter(Integer value) {
		this.tileCenter.setValue(value);
	}

	public Integer getTileCenterRight() {
		return tileCenterRight.getValue();
	}
	
	public void setTileCenterRight(Integer value) {
		this.tileCenterRight.setValue(value);
	}

	public Integer getTileCenterLeft() {
		return tileCenterLeft.getValue();
	}

	public void setTileCenterLeft(Integer value) {
		this.tileCenterLeft.setValue(value);
	}
	
	public Integer getTileBottomCenter() {
		return tileBottomCenter.getValue();
	}

	public void setTileBottomCenter(Integer value) {
		this.tileBottomCenter.setValue(value);
	}
	
	public Integer getTileBottomRight() {
		return tileBottomRight.getValue();
	}

	public void setTileBottomRight(Integer value) {
		this.tileBottomRight.setValue(value);
	}
	
	public Integer getTileBottomLeft() {
		return tileBottomLeft.getValue();
	}

	public void setTileBottomLeft(Integer value) {
		this.tileBottomLeft.setValue(value);
	}

	public Integer getTileTopCenter() {
		return tileTopCenter.getValue();
	}
	
	public void setTileTopCenter(Integer value) {
		this.tileTopCenter.setValue(value);
	}
	
	public Integer getTileTopRight() {
		return tileTopRight.getValue();
	}

	public void setTileTopRight(Integer value) {
		this.tileTopRight.setValue(value);
	}
	
	public Integer getTileTopLeft() {
		return tileTopLeft.getValue();
	}

	public void setTileTopLeft(Integer value) {
		this.tileTopLeft.setValue(value);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void generateTiles() {
		tileTopLeft = new Tile(7, this);
		tileTopCenter = new Tile(2, this);
		tileTopRight = new Tile(4, this);
		tileCenterLeft = new Tile(5, this);
		tileCenter = new Tile(0, this);
		tileCenterRight = new Tile(6, this);
		tileBottomLeft = new Tile(8, this);
		tileBottomCenter = new Tile(3, this);
		tileBottomRight = new Tile(1, this);

		linkNeighbors();
		setPointer(tileCenter);
	}

	private void linkNeighbors() {
		tileTopLeft.setDown(tileCenterLeft);
		tileTopLeft.setRight(tileTopCenter);

		tileTopCenter.setDown(tileCenter);
		tileTopCenter.setRight(tileTopRight);
		tileTopCenter.setLeft(tileTopLeft);

		tileTopRight.setDown(tileCenterRight);
		tileTopRight.setLeft(tileTopCenter);

		tileCenterLeft.setRight(tileCenter);
		tileCenterLeft.setDown(tileBottomLeft);
		tileCenterLeft.setUp(tileTopLeft);

		tileCenter.setDown(tileBottomCenter);
		tileCenter.setUp(tileTopCenter);
		tileCenter.setRight(tileCenterRight);
		tileCenter.setLeft(tileCenterLeft);

		tileCenterRight.setDown(tileBottomRight);
		tileCenterRight.setUp(tileTopRight);
		tileCenterRight.setLeft(tileCenter);

		tileBottomLeft.setUp(tileCenterLeft);
		tileBottomLeft.setRight(tileBottomCenter);

		tileBottomCenter.setUp(tileCenter);
		tileBottomCenter.setRight(tileBottomRight);
		tileBottomCenter.setLeft(tileBottomLeft);

		tileBottomRight.setUp(tileCenterRight);
		tileBottomRight.setLeft(tileBottomCenter);
	}
	
	public Boolean checkGameOver() {
        return getTileTopLeft().equals(1)
                && getTileTopCenter().equals(2)
                && getTileTopRight().equals(3)
                && getTileCenterLeft().equals(4)
                && getTileCenter().equals(5)
                && getTileCenterRight().equals(6)
                && getTileBottomLeft().equals(7)
                && getTileBottomCenter().equals(8)
                && getTileBottomRight().equals(0);
    }

	@Override
	public String toString() {
		return String.format("|%d %d %d|\n|%d %d %d|\n|%d %d %d|", 
				tileTopLeft.getValue(), tileTopCenter.getValue(),
				tileTopRight.getValue(), tileCenterLeft.getValue(),
				tileCenter.getValue(), tileCenterRight.getValue(),
				tileBottomLeft.getValue(), tileBottomCenter.getValue(),
				tileBottomRight.getValue());
	}
}
