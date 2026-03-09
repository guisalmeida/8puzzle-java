package com.guisalmeida.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {
	private Board board;

	private Tile tileCenter;
	private Tile tileUp;
	private Tile tileRight;
	private Tile tileLeft;
	private Tile tileDown;

	@BeforeEach
	public void setup() {
		board = new Board();

		tileCenter = new Tile(5, board);
		tileUp = new Tile(1, board);
		tileRight = new Tile(2, board);
		tileLeft = new Tile(3, board);
		tileDown = new Tile(4, board);
	}

	@Test
	public void shouldCreateTile() {
		Tile tileBottomCenter = new Tile(8, board);
		assertEquals(Integer.valueOf(8), tileBottomCenter.getValue());
	}

	@Test
	public void shouldAddNeighborTiles() {
		tileCenter.setDown(tileDown);
		tileCenter.setRight(tileRight);
		tileCenter.setLeft(tileLeft);
		tileCenter.setUp(tileUp);

		assertEquals(Integer.valueOf(1), tileCenter.getUp().getValue());
		assertEquals(Integer.valueOf(2), tileCenter.getRight().getValue());
		assertEquals(Integer.valueOf(3), tileCenter.getLeft().getValue());
		assertEquals(Integer.valueOf(4), tileCenter.getDown().getValue());
	}

	@Test
	public void shouldMoveTileToUpNeighbor() {
		tileCenter.setUp(tileUp);
		tileCenter.moveUp();
		assertEquals(Integer.valueOf(1), tileCenter.getValue());
		assertEquals(Integer.valueOf(5), tileUp.getValue());
	}

	@Test
	public void shouldMoveTileToRightNeighbor() {
		tileCenter.setRight(tileRight);
		tileCenter.moveRight();
		assertEquals(Integer.valueOf(2), tileCenter.getValue());
		assertEquals(Integer.valueOf(5), tileRight.getValue());
	}

	@Test
	public void shouldMoveTileToDownNeighbor() {
		tileCenter.setDown(tileDown);
		tileCenter.moveDown();
		assertEquals(Integer.valueOf(4), tileCenter.getValue());
		assertEquals(Integer.valueOf(5), tileDown.getValue());
	}

	@Test
	public void shouldMoveTileToLeftNeighbor() {
		tileCenter.setLeft(tileLeft);
		tileCenter.moveLeft();
		assertEquals(Integer.valueOf(3), tileCenter.getValue());
		assertEquals(Integer.valueOf(5), tileLeft.getValue());
	}

	@Test
	public void shouldNotChangeValueWhenBorder() {
		tileRight.moveRight();
		assertEquals(Integer.valueOf(2), tileRight.getValue());
	}
}
