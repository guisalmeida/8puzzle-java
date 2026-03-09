package com.guisalmeida.eightpuzzle.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {
	private Integer id;

	private final Integer tileTopLeft;
	private final Integer tileTopCenter;
	private final Integer tileTopRight;
	private final Integer tileCenterLeft;
	private final Integer tileCenter;
	private final Integer tileCenterRight;
	private final Integer tileBottomLeft;
	private final Integer tileBottomCenter;
	private final Integer tileBottomRight;
	private final ConnectionFactory connectionFactory;

	public BoardDAO(Board board) {
		this(board, new ConnectionFactory());
	}

	public BoardDAO(Board board, ConnectionFactory connectionFactory) {
		tileTopLeft = board.getTileTopLeft();
		tileTopCenter = board.getTileTopCenter();
		tileTopRight = board.getTileTopRight();
		tileCenterLeft = board.getTileCenterLeft();
		tileCenter = board.getTileCenter();
		tileCenterRight = board.getTileCenterRight();
		tileBottomLeft = board.getTileBottomLeft();
		tileBottomCenter = board.getTileBottomCenter();
		tileBottomRight = board.getTileBottomRight();
		this.connectionFactory = connectionFactory;
	}

	public void insert() throws SQLException {
		Connection connection;
		String sql;
		PreparedStatement statement;
		ResultSet resultSet;

		try {
			connection = connectionFactory.getConnection();
			sql = "INSERT INTO board (tile_top_left, tile_top_center, tile_top_right,\n"
					+ "tile_center_left, tile_center, tile_center_right,\n"
					+ "tile_bottom_left, tile_bottom_center, tile_bottom_right)\n"
					+ "VALUES (?,?,?,?,?,?,?,?,?) RETURNING id;";

			statement = getPreparedStatement(connection, sql);

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

	private PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
		PreparedStatement statement;
		statement = connection.prepareStatement(sql);

		statement.setInt(1, tileTopLeft);
		statement.setInt(2, tileTopCenter);
		statement.setInt(3, tileTopRight);
		statement.setInt(4, tileCenterLeft);
		statement.setInt(5, tileCenter);
		statement.setInt(6, tileCenterRight);
		statement.setInt(7, tileBottomLeft);
		statement.setInt(8, tileBottomCenter);
		statement.setInt(9, tileBottomRight);
		return statement;
	}

	public void update(Integer boardId) throws SQLException {
		Connection connection;
		String sql;
		PreparedStatement statement;
		
		try {
			connection = connectionFactory.getConnection();
			sql = "UPDATE board SET tile_top_left = ?, tile_top_center = ?, tile_top_right = ?,\n"
					+ "tile_center_left = ?, tile_center = ?, tile_center_right = ?,\n"
					+ "tile_bottom_left = ?, tile_bottom_center = ?, tile_bottom_right = ? "
					+ "WHERE id = ?;";

			statement = getPreparedStatement(connection, sql);
			statement.setInt(10, boardId);

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
