package com.guisalmeida.eightpuzzle.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
	private Integer id;

	private final String name;
	private final Integer moves;
	private final Boolean winner;
	private final ConnectionFactory connectionFactory;

	public PlayerDAO(Player player) {
		this(player, new ConnectionFactory());
	}

	public PlayerDAO(Player player, ConnectionFactory connectionFactory) {
		this.name = player.getName();
		this.moves = player.getMoves();
		this.winner = player.isWinner();
		this.connectionFactory = connectionFactory;
	}

	public void insert(Integer boardId) throws SQLException {
		Connection connection;
		String sql;
		ResultSet resultSet;

		try {
			connection = connectionFactory.getConnection();
			sql = "INSERT INTO player (name, moves, winner, board_id) VALUES (?,?,?,?) RETURNING id;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, moves);
			statement.setBoolean(3, winner);
			statement.setInt(4, boardId);
			statement.execute();

			resultSet = statement.getResultSet();

			resultSet.next();
			id = resultSet.getInt("id");

			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Integer playerId) throws SQLException {
		Connection connection;
		String sql;
		PreparedStatement statement;
		
		try {
			connection = connectionFactory.getConnection();
			sql = "UPDATE player SET moves = ?, winner = ? WHERE id = ?;";

			statement = connection.prepareStatement(sql);

			statement.setInt(1, moves);
			statement.setBoolean(2, winner);
			statement.setInt(3, playerId);

			statement.execute();
			
			statement.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Integer getId() {
		return id;
	}
}
