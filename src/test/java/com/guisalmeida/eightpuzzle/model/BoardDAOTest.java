package com.guisalmeida.eightpuzzle.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardDAOTest {
	private ConnectionFactory mockConnectionFactory;
    private PreparedStatement mockStatement;

    @BeforeEach
	public void setup() throws SQLException {
		mockConnectionFactory = mock(ConnectionFactory.class);
        Connection mockConnection = mock(Connection.class);
		mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

		when(mockConnectionFactory.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.getResultSet()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt("id")).thenReturn(1);
	}

	@Test
	public void shouldInsertNewBoard() throws SQLException {
		BoardDAO boardDAO = new BoardDAO(new Board(), mockConnectionFactory);

		boardDAO.insert();

		assertNotNull(boardDAO.getId());
		verify(mockStatement).execute();
	}
}
