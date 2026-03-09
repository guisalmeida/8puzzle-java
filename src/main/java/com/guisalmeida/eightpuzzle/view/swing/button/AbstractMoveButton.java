package com.guisalmeida.eightpuzzle.view.swing.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.guisalmeida.eightpuzzle.control.BoardController;
import com.guisalmeida.eightpuzzle.model.Board;
import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.view.swing.view.ControlView;
import com.guisalmeida.eightpuzzle.view.swing.view.BoardView;

public abstract class AbstractMoveButton extends JButton implements ActionListener {
	private Board board;
	private Player player;
	private BoardController boardController;
	private BoardView boardView;
	private ControlView controlView;

	public AbstractMoveButton(String direction, Board board,
		BoardView boardView, ControlView controlView, Player player) {
		setText(direction);
		addActionListener(this);
		setBoard(board);
		setPlayer(player);
		setBoardController(new BoardController(board));
		setBoardView(boardView);
		setControlView(controlView);
	}

	public abstract void updateBoardState();

	public BoardController getBoardController() {
		return boardController;
	}

	public void setBoardController(BoardController controller) {
		this.boardController = controller;
	}

	public Board getBoard() {
		return board;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public ControlView getControlView() {
		return controlView;
	}

	public void setControlView(ControlView controlView) {
		this.controlView = controlView;
	}
}
