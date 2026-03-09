package com.guisalmeida.eightpuzzle.view.swing.button;

import java.awt.event.ActionEvent;

import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.model.Board;
import com.guisalmeida.eightpuzzle.view.swing.view.ControlView;
import com.guisalmeida.eightpuzzle.view.swing.view.BoardView;

public class MoveUpButton extends AbstractMoveButton {

	public MoveUpButton(String direction, Board board,
		BoardView boardView, ControlView controlView, Player player) {
		super(direction, board, boardView, controlView, player);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		updateBoardState();
	}

	@Override
	public void updateBoardState() {
		this.getBoardController().moveUp();
		this.getBoardView().updateBoardView(this.getBoard());
		Integer moves = this.getPlayer().getMoves();
		this.getPlayer().setMoves(moves + 1);
	};
}
