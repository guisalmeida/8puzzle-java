package com.guisalmeida.eightpuzzle.model.DAO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.guisalmeida.eightpuzzle.model.ConnectionFactory;
import com.guisalmeida.eightpuzzle.model.PlayerDAO;
import org.junit.Before;
import org.junit.Test;

import com.guisalmeida.eightpuzzle.model.Player;

public class PlayerDAOTest {
	private ConnectionFactory mockConnectionFactory;
    private PreparedStatement mockStatement;

    @Before
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
	public void shouldInsertNewPlayer() throws SQLException {
		Player player = new Player("Gui");
		PlayerDAO playerDAO = new PlayerDAO(player, mockConnectionFactory);

		playerDAO.insert(8);

		assertNotNull(playerDAO.getId());
		verify(mockStatement).execute();
	}
}
