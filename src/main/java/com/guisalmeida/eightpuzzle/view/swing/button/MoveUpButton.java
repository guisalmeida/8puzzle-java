package com.guisalmeida.eightpuzzle.view.swing.button;

import java.awt.event.ActionEvent;

import com.guisalmeida.eightpuzzle.control.BoardController;
import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.view.swing.view.ControlView;
import com.guisalmeida.eightpuzzle.view.swing.view.BoardView;

public class MoveUpButton extends AbstractMoveButton {

	public MoveUpButton(String direction, BoardController boardController,
		BoardView boardView, ControlView controlView, Player player) {
		super(direction, boardController, boardView, controlView, player);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		updateBoardState();
	}

	@Override
	public void updateBoardState() {
		getBoardController().moveUp();
		getBoardView().updateBoardView(getBoardController().getBoard());
		getPlayer().setMoves(getPlayer().getMoves() + 1);
	}
}
