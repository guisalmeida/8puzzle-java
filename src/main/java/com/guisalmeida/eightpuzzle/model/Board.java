package com.guisalmeida.eightpuzzle.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Access(AccessType.PROPERTY)
public class Board {
	private Tile pointer;
	private Tile tileCenterTile;
	private Tile tileCenterRightTile;
	private Tile tileCenterLeftTile;
	private Tile tileBottomCenterTile;
	private Tile tileBottomRightTile;
	private Tile tileBottomLeftTile;
	private Tile tileTopCenterTile;
	private Tile tileTopRightTile;
	private Tile tileTopLeftTile;

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
		return tileCenterTile.getValue();
	}
	
	public void setTileCenter(Integer value) {
		this.tileCenterTile.setValue(value);
	}

	@Column(name = "tile_center_right")
	public Integer getTileCenterRight() {
		return tileCenterRightTile.getValue();
	}
	
	public void setTileCenterRight(Integer value) {
		this.tileCenterRightTile.setValue(value);
	}

	@Column(name = "tile_center_left")
	public Integer getTileCenterLeft() {
		return tileCenterLeftTile.getValue();
	}

	public void setTileCenterLeft(Integer value) {
		this.tileCenterLeftTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_center")
	public Integer getTileBottomCenter() {
		return tileBottomCenterTile.getValue();
	}

	public void setTileBottomCenter(Integer value) {
		this.tileBottomCenterTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_right")
	public Integer getTileBottomRight() {
		return tileBottomRightTile.getValue();
	}

	public void setTileBottomRight(Integer value) {
		this.tileBottomRightTile.setValue(value);
	}
	
	@Column(name = "tile_bottom_left")
	public Integer getTileBottomLeft() {
		return tileBottomLeftTile.getValue();
	}

	public void setTileBottomLeft(Integer value) {
		this.tileBottomLeftTile.setValue(value);
	}

	@Column(name = "tile_top_center")
	public Integer getTileTopCenter() {
		return tileTopCenterTile.getValue();
	}
	
	public void setTileTopCenter(Integer value) {
		this.tileTopCenterTile.setValue(value);
	}
	
	@Column(name = "tile_top_right")
	public Integer getTileTopRight() {
		return tileTopRightTile.getValue();
	}

	public void setTileTopRight(Integer value) {
		this.tileTopRightTile.setValue(value);
	}
	
	@Column(name = "tile_top_left")
	public Integer getTileTopLeft() {
		return tileTopLeftTile.getValue();
	}

	public void setTileTopLeft(Integer value) {
		this.tileTopLeftTile.setValue(value);
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
		tileTopLeftTile = new Tile(7, this);
		tileTopCenterTile = new Tile(2, this);
		tileTopRightTile = new Tile(4, this);
		tileCenterLeftTile = new Tile(5, this);
		tileCenterTile = new Tile(0, this);
		tileCenterRightTile = new Tile(6, this);
		tileBottomLeftTile = new Tile(8, this);
		tileBottomCenterTile = new Tile(3, this);
		tileBottomRightTile = new Tile(1, this);

		linkNeighbors();
		setPointer(tileCenterTile);
	}

	private void linkNeighbors() {
		tileTopLeftTile.setDown(tileCenterLeftTile);
		tileTopLeftTile.setRight(tileTopCenterTile);

		tileTopCenterTile.setDown(tileCenterTile);
		tileTopCenterTile.setRight(tileTopRightTile);
		tileTopCenterTile.setLeft(tileTopLeftTile);

		tileTopRightTile.setDown(tileCenterRightTile);
		tileTopRightTile.setLeft(tileTopCenterTile);

		tileCenterLeftTile.setRight(tileCenterTile);
		tileCenterLeftTile.setDown(tileBottomLeftTile);
		tileCenterLeftTile.setUp(tileTopLeftTile);

		tileCenterTile.setDown(tileBottomCenterTile);
		tileCenterTile.setUp(tileTopCenterTile);
		tileCenterTile.setRight(tileCenterRightTile);
		tileCenterTile.setLeft(tileCenterLeftTile);

		tileCenterRightTile.setDown(tileBottomRightTile);
		tileCenterRightTile.setUp(tileTopRightTile);
		tileCenterRightTile.setLeft(tileCenterTile);

		tileBottomLeftTile.setUp(tileCenterLeftTile);
		tileBottomLeftTile.setRight(tileBottomCenterTile);

		tileBottomCenterTile.setUp(tileCenterTile);
		tileBottomCenterTile.setRight(tileBottomRightTile);
		tileBottomCenterTile.setLeft(tileBottomLeftTile);

		tileBottomRightTile.setUp(tileCenterRightTile);
		tileBottomRightTile.setLeft(tileBottomCenterTile);
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
				tileTopLeftTile.getValue(), tileTopCenterTile.getValue(),
				tileTopRightTile.getValue(), tileCenterLeftTile.getValue(),
				tileCenterTile.getValue(), tileCenterRightTile.getValue(),
				tileBottomLeftTile.getValue(), tileBottomCenterTile.getValue(),
				tileBottomRightTile.getValue());
	}
}
