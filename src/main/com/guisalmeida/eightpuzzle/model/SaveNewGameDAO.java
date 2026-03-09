package com.guisalmeida.eightpuzzle.model;

import java.sql.SQLException;

public class SaveNewGameDAO {
	private final Board board;
	private final Player player;
	private final ConnectionFactory connectionFactory;
	private Integer boardId;
	private Integer playerId;

	public SaveNewGameDAO(Board board, Player player) {
		this(board, player, new ConnectionFactory());
	}

	public SaveNewGameDAO(Board board, Player player, ConnectionFactory connectionFactory) {
		this.board = board;
		this.player = player;
		this.connectionFactory = connectionFactory;
	}

	public void save() {
		BoardDAO boardDAO = new BoardDAO(this.board, connectionFactory);

		try {
			boardDAO.insert();
			boardId = boardDAO.getId();

			PlayerDAO playerDAO = new PlayerDAO(this.player, connectionFactory);
			playerDAO.insert(boardId);
			playerId = playerDAO.getId();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Integer getBoardId() {
		return boardId;
	}
	
	public Integer getPlayerId() {
		return playerId;
	}
}
