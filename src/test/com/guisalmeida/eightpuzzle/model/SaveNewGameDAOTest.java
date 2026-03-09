package com.guisalmeida.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveNewGameDAOTest {
	private ConnectionFactory mockConnectionFactory;

    @BeforeEach
	public void setup() throws SQLException {
		mockConnectionFactory = mock(ConnectionFactory.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

		when(mockConnectionFactory.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt("id")).thenReturn(1);
	}

	@Test
	public void shouldSaveNewGame() {
		Board board = new Board();
		Player player = new Player("Gui");

		SaveNewGameDAO saveNewGameDAO = new SaveNewGameDAO(board, player, mockConnectionFactory);
		saveNewGameDAO.save();

		assertNotNull(saveNewGameDAO.getBoardId());
		assertNotNull(saveNewGameDAO.getPlayerId());
	}
}
