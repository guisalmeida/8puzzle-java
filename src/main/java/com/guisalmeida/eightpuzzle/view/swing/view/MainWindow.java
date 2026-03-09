package com.guisalmeida.eightpuzzle.view.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.model.Board;
import com.guisalmeida.eightpuzzle.model.PlayerDAO;
import com.guisalmeida.eightpuzzle.model.SaveNewGameDAO;
import com.guisalmeida.eightpuzzle.model.BoardDAO;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Board board;
	private Player player;

	public void startGame() {
		String playerName = JOptionPane.showInputDialog("Enter your name to play:");
		player = new Player(playerName);
		board = new Board();
		setupLayout(this.board);
		saveNewGame(this.board);
		setupCloseHandler(this.board, this.player);
	}

	private void setupLayout(Board board) {
		BoardView boardView = new BoardView(board);
		ControlView controlView = new ControlView(board, boardView, player);
		addKeyListener(controlView);

		GridBagLayout gridBagLayout = (GridBagLayout) controlView.getLayout();
		gridBagLayout.columnWidths = new int[] { 0, 200, 0, 0, 0, 0, 0 };
		controlView.setPreferredSize(new Dimension(600, 100));

		getContentPane().setLayout(new BorderLayout());
		setTitle("8 Puzzle");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);

		add(boardView, BorderLayout.CENTER);
		add(controlView, BorderLayout.SOUTH);
	}

	private void saveNewGame(Board board) {
		SaveNewGameDAO saveNewGame = new SaveNewGameDAO(board, player);
		saveNewGame.save();
		board.setId(saveNewGame.getBoardId());
		player.setId(saveNewGame.getPlayerId());
	}

	private void setupCloseHandler(Board board, Player player) {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit?", "Close window?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						BoardDAO boardDAO = new BoardDAO(board);
						PlayerDAO playerDAO = new PlayerDAO(player);
						boardDAO.update(board.getId());
						playerDAO.update(player.getId());

						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						System.exit(0);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
}
