package br.ies.aps.eightpuzzle.view.swing.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import br.ies.aps.eightpuzzle.model.Board;

@SuppressWarnings("serial")
public class BoardView extends JPanel {
	private TileView tileTopLeft;
	private TileView tileTopCenter;
	private TileView tileTopRight;
	private TileView tileCenterLeft;
	private TileView tileCenter;
	private TileView tileCenterRight;
	private TileView tileBottomLeft;
	private TileView tileBottomCenter;
	private TileView tileBottomRight;

	public BoardView(Board board) {

		setLayout(new GridLayout(3, 3));

		tileTopLeft = new TileView(board.getTileTopLeft());
		add(tileTopLeft.getLabel());

		tileTopCenter = new TileView(board.getTileTopCenter());
		add(tileTopCenter.getLabel());

		tileTopRight = new TileView(board.getTileTopRight());
		add(tileTopRight.getLabel());

		tileCenterLeft = new TileView(board.getTileCenterLeft());
		add(tileCenterLeft.getLabel());

		tileCenter = new TileView(board.getTileCenter());
		add(tileCenter.getLabel());

		tileCenterRight = new TileView(board.getTileCenterRight());
		add(tileCenterRight.getLabel());

		tileBottomLeft = new TileView(board.getTileBottomLeft());
		add(tileBottomLeft.getLabel());

		tileBottomCenter = new TileView(board.getTileBottomCenter());
		add(tileBottomCenter.getLabel());

		tileBottomRight = new TileView(board.getTileBottomRight());
		add(tileBottomRight.getLabel());
	}

	public void updateBoardView(Board board) {
		tileTopLeft.setLabel(board.getTileTopLeft());
		tileTopCenter.setLabel(board.getTileTopCenter());
		tileTopRight.setLabel(board.getTileTopRight());
		tileCenterLeft.setLabel(board.getTileCenterLeft());
		tileCenter.setLabel(board.getTileCenter());
		tileCenterRight.setLabel(board.getTileCenterRight());
		tileBottomLeft.setLabel(board.getTileBottomLeft());
		tileBottomCenter.setLabel(board.getTileBottomCenter());
		tileBottomRight.setLabel(board.getTileBottomRight());
	}
}
