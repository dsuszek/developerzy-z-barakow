package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseConnectorTest {
    DatabaseConnector databaseConnectorMock = mock(DatabaseConnector.class);
    Connection connMock = mock(Connection.class);
    DatabaseConnector databaseConnector = new DatabaseConnector();

    @Test
    void getDatabaseConnected_thenReturnTrue() throws SQLException {
        when(databaseConnectorMock.isDbConnected(databaseConnectorMock.getConnection())).thenReturn(true);
        Assertions.assertTrue(databaseConnector.isDbConnected(connMock));
    }
}
