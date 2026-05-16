package com.guisalmeida.eightpuzzle.view.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.guisalmeida.eightpuzzle.control.BoardController;
import com.guisalmeida.eightpuzzle.model.Player;
import com.guisalmeida.eightpuzzle.model.Board;
import com.guisalmeida.eightpuzzle.model.BoardDAO;
import com.guisalmeida.eightpuzzle.model.PlayerDAO;
import com.guisalmeida.eightpuzzle.model.SaveNewGameDAO;
import com.guisalmeida.eightpuzzle.model.PersistenceManager;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Board board;
	private Player player;

	public void startGame() {
		String playerName = JOptionPane.showInputDialog("Enter your name to play:");
		player = new Player(playerName);
		board = new Board();
		BoardController boardController = new BoardController(board);
		setupLayout(boardController);
		saveNewGame(this.board);
		setupCloseHandler(this.board, this.player);
	}

	private void setupLayout(BoardController boardController) {
		BoardView boardView = new BoardView(boardController.getBoard());
		ControlView controlView = new ControlView(boardController, boardView, player);
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
	}

	private void setupCloseHandler(Board board, Player player) {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit?", "Close window?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					BoardDAO boardDAO = new BoardDAO();
					boardDAO.update(board);
					boardDAO.close();

					PlayerDAO playerDAO = new PlayerDAO();
					playerDAO.update(player);
					playerDAO.close();

					PersistenceManager.close();
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					System.exit(0);
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
}
