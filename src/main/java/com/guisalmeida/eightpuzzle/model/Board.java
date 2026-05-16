package com.guisalmeida.eightpuzzle.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Access(AccessType.PROPERTY)
public class Board {
	private Tile pointer;
	private Tile centerTile;
	private Tile centerRightTile;
	private Tile centerLeftTile;
	private Tile bottomCenterTile;
	private Tile bottomRightTile;
	private Tile bottomLeftTile;
	private Tile topCenterTile;
	private Tile topRightTile;
	private Tile topLeftTile;

	private Integer id;

	private final List<BoardObserver> observers = new ArrayList<>();

	public Board() {
		generateTiles();
	}

	@Transient
	public List<BoardObserver> getObservers() {
		return observers;
	}

	public void registerObserver(BoardObserver observer) {
		observers.add(observer);
	}

	public void notifyObservers(Board board) {
		for (BoardObserver observer : observers) {
			observer.onBoardStateChanged(this);
		}
	}

	@Transient
	public Tile getPointer() {
		return pointer;
	}

	public void setPointer(Tile pointer) {
		this.pointer = pointer;
	}

	@Column(name = "tile_center")
	public Integer getTileCenter() {
		return centerTile.getValue();
	}
	
	public void setTileCenter(Integer value) {
		this.centerTile.setValue(value);
	}

	@Column(name = "tile_center_right")
	public Integer getTileCenterRight() {
		return centerRightTile.getValue();
	}
	
	public void setTileCenterRight(Integer value) {
		this.centerRightTile.setValue(value);
	}

	@Column(name = "tile_center_left")
	public Integer getTileCenterLeft() {
		return centerLeftTile.getValue();
	}

	public void setTileCenterLeft(Integer value) {
		this.centerLeftTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_center")
	public Integer getTileBottomCenter() {
		return bottomCenterTile.getValue();
	}

	public void setTileBottomCenter(Integer value) {
		this.bottomCenterTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_right")
	public Integer getTileBottomRight() {
		return bottomRightTile.getValue();
	}

	public void setTileBottomRight(Integer value) {
		this.bottomRightTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_left")
	public Integer getTileBottomLeft() {
		return bottomLeftTile.getValue();
	}

	public void setTileBottomLeft(Integer value) {
		this.bottomLeftTile.setValue(value);
	}

	@Column(name = "tile_top_center")
	public Integer getTileTopCenter() {
		return topCenterTile.getValue();
	}
	
	public void setTileTopCenter(Integer value) {
		this.topCenterTile.setValue(value);
	}
	
	@Column(name = "tile_top_right")
	public Integer getTileTopRight() {
		return topRightTile.getValue();
	}

	public void setTileTopRight(Integer value) {
		this.topRightTile.setValue(value);
	}
	
	@Column(name = "tile_top_left")
	public Integer getTileTopLeft() {
		return topLeftTile.getValue();
	}

	public void setTileTopLeft(Integer value) {
		this.topLeftTile.setValue(value);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void generateTiles() {
		topLeftTile = new Tile(7, this);
		topCenterTile = new Tile(2, this);
		topRightTile = new Tile(4, this);
		centerLeftTile = new Tile(5, this);
		centerTile = new Tile(0, this);
		centerRightTile = new Tile(6, this);
		bottomLeftTile = new Tile(8, this);
		bottomCenterTile = new Tile(3, this);
		bottomRightTile = new Tile(1, this);

		linkNeighbors();
		setPointer(centerTile);
	}

	private void linkNeighbors() {
		topLeftTile.setDown(centerLeftTile);
		topLeftTile.setRight(topCenterTile);

		topCenterTile.setDown(centerTile);
		topCenterTile.setRight(topRightTile);
		topCenterTile.setLeft(topLeftTile);

		topRightTile.setDown(centerRightTile);
		topRightTile.setLeft(topCenterTile);

		centerLeftTile.setRight(centerTile);
		centerLeftTile.setDown(bottomLeftTile);
		centerLeftTile.setUp(topLeftTile);

		centerTile.setDown(bottomCenterTile);
		centerTile.setUp(topCenterTile);
		centerTile.setRight(centerRightTile);
		centerTile.setLeft(centerLeftTile);

		centerRightTile.setDown(bottomRightTile);
		centerRightTile.setUp(topRightTile);
		centerRightTile.setLeft(centerTile);

		bottomLeftTile.setUp(centerLeftTile);
		bottomLeftTile.setRight(bottomCenterTile);

		bottomCenterTile.setUp(centerTile);
		bottomCenterTile.setRight(bottomRightTile);
		bottomCenterTile.setLeft(bottomLeftTile);

		bottomRightTile.setUp(centerRightTile);
		bottomRightTile.setLeft(bottomCenterTile);
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
				topLeftTile.getValue(), topCenterTile.getValue(),
				topRightTile.getValue(), centerLeftTile.getValue(),
				centerTile.getValue(), centerRightTile.getValue(),
				bottomLeftTile.getValue(), bottomCenterTile.getValue(),
				bottomRightTile.getValue());
	}
}
