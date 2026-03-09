package br.ies.aps.eightpuzzle.view.swing.button;

import java.awt.event.ActionEvent;

import br.ies.aps.eightpuzzle.model.Board;
import br.ies.aps.eightpuzzle.model.Player;
import br.ies.aps.eightpuzzle.view.swing.view.ControlView;
import br.ies.aps.eightpuzzle.view.swing.view.BoardView;

public class MoveDownButton extends AbstractMoveButton {

	public MoveDownButton(String direction, Board board,
		BoardView boardView, ControlView controlView, Player player) {
		super(direction, board, boardView, controlView, player);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		updateBoardState();
	}

	@Override
	public void updateBoardState() {
		this.getBoardController().moveDown();
		this.getBoardView().updateBoardView(this.getBoard());
		Integer moves = this.getPlayer().getMoves();
		this.getPlayer().setMoves(moves + 1);
	}
}
