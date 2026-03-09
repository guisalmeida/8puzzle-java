package com.guisalmeida.eightpuzzle.view.swing.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.guisalmeida.eightpuzzle.model.Board;
import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.model.BoardObserver;
import com.guisalmeida.eightpuzzle.model.PlayerDAO;
import com.guisalmeida.eightpuzzle.view.swing.button.MoveDownButton;
import com.guisalmeida.eightpuzzle.view.swing.button.MoveUpButton;
import com.guisalmeida.eightpuzzle.view.swing.button.MoveRightButton;
import com.guisalmeida.eightpuzzle.view.swing.button.MoveLeftButton;

public class ControlView extends JPanel implements KeyListener, BoardObserver {
	private final Board board;
	private final Player player;
	private final BoardView boardView;
	private JLabel movesLabel;
	private MoveUpButton buttonUp;
	private MoveDownButton buttonDown;
	private MoveRightButton buttonRight;
	private MoveLeftButton buttonLeft;

	public ControlView(Board board, BoardView boardView, Player player) {
		this.board = board;
		this.boardView = boardView;
		this.player = player;

		generateControlLayout();
		this.board.registerObserver(this);
	}

	private void generateControlLayout() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints position = new GridBagConstraints();
		position.fill = GridBagConstraints.HORIZONTAL;

		setLayout(layout);

		position.gridy = 0;
		position.gridx = 0;
		JLabel playerLabel = new JLabel(String.format("Player: %s", player.getName()));
		add(playerLabel, position);

		position.gridy = 1;
		position.gridx = 0;
		position.gridwidth = 3;
		movesLabel = new JLabel(String.format("Moves: %d", player.getMoves()));
		add(movesLabel, position);

		position.gridwidth = 1;
		position.gridy = 0;
		position.gridx = 5;
		buttonUp = new MoveUpButton("↑", board, boardView, this, player);
		add(buttonUp, position);

		position.gridy = 2;
		position.gridx = 5;
		buttonDown = new MoveDownButton("↓", board, boardView, this, player);
		add(buttonDown, position);

		position.gridy = 1;
		position.gridx = 6;
		buttonRight = new MoveRightButton("→", board, boardView, this, player);
		add(buttonRight, position);

		position.gridy = 1;
		position.gridx = 4;
		buttonLeft = new MoveLeftButton("←", board, boardView, this, player);
		add(buttonLeft, position);
	}

	public void updateMoves(Integer count) {
		movesLabel.setText(String.format("Moves: %d", count));
	}

	public void finishGame(Integer count) {
		movesLabel.setText(String.format("You won with %d moves!!", count));
	}

	public void checkGameOver(Board board) {
		if (board.checkGameOver()) {
			finishGame(player.getMoves() + 1);
			player.setWinner(true);
			PlayerDAO playerDAO = new PlayerDAO(player);
			try {
				playerDAO.update(player.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			updateMoves(player.getMoves() + 1);
		}
	}

	@Override
	public void onBoardStateChanged(Board board) {
		checkGameOver(board);
	};

	@Override
	public void keyPressed(KeyEvent event) {
		Map<Integer, Runnable> keyMap = new HashMap<Integer, Runnable>();

		keyMap.put(KeyEvent.VK_DOWN, new Runnable() {
			@Override
			public void run() {
				buttonDown.updateBoardState();
			}
		});
		keyMap.put(KeyEvent.VK_UP, new Runnable() {
			@Override
			public void run() {
				buttonUp.updateBoardState();
			}
		});
		keyMap.put(KeyEvent.VK_RIGHT, new Runnable() {
			@Override
			public void run() {
				buttonRight.updateBoardState();
			}
		});
		keyMap.put(KeyEvent.VK_LEFT, new Runnable() {
			@Override
			public void run() {
				buttonLeft.updateBoardState();
			}
		});
		
		// TODO check if key is in the map before running
		keyMap.get(event.getKeyCode()).run();
	}

	@Override
	public void keyReleased(KeyEvent event) {
	};

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
