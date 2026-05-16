package com.guisalmeida.eightpuzzle.view.swing.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.guisalmeida.eightpuzzle.control.BoardController;
import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.view.swing.view.ControlView;
import com.guisalmeida.eightpuzzle.view.swing.view.BoardView;

public abstract class AbstractMoveButton extends JButton implements ActionListener {
	private final BoardController boardController;
	private final BoardView boardView;
	private final ControlView controlView;
	private final Player player;

	public AbstractMoveButton(String direction, BoardController boardController,
		BoardView boardView, ControlView controlView, Player player) {
		setText(direction);
		addActionListener(this);
		this.boardController = boardController;
		this.boardView = boardView;
		this.controlView = controlView;
		this.player = player;
	}

	public abstract void updateBoardState();

	public BoardController getBoardController() {
		return boardController;
	}

	public Player getPlayer() {
		return player;
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public ControlView getControlView() {
		return controlView;
	}
}
