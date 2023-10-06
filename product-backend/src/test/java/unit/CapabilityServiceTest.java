package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.service.CapabilityService;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDaoMock = mock(CapabilityDao.class);
    DatabaseConnector databaseConnectorMock = mock(DatabaseConnector.class);
    Connection connMock = mock(Connection.class);

    Connection conn;
    CapabilityService capabilityService = new CapabilityService(capabilityDaoMock, databaseConnectorMock);

    @Test
    void getCapabilites_whenCapabilitiesAvailable_shouldReturnListOfCapabilities() throws SQLException, FailedToGetCapabilitiesException {
        //given
        CapabilityService sut = new CapabilityService(capabilityDaoMock, databaseConnectorMock);
        when(databaseConnectorMock.getConnection()).thenReturn(conn);
        List<Capability> expectedList = List.of(new Capability(), new Capability(), new Capability());
        when(capabilityDaoMock.getCapabilities(conn)).thenReturn(expectedList);
        //when
        List<Capability> result = sut.getCapabilities();
        //then
        Assertions.assertEquals(result, expectedList);
    }

    @Test
    void getCapabilities_whenCapabilitiesAreUnavailable_shouldReturnEmptyList() throws SQLException {
        //given
        when(databaseConnectorMock.getConnection()).thenReturn(conn);
        //when
        List<Capability> testCapabilityList = new ArrayList<>();
        when(capabilityDaoMock.getCapabilities(conn)).thenReturn(testCapabilityList);
        //then
        Assertions.assertTrue(testCapabilityList.isEmpty());
    }
    @Test
    void getCapabilities_When_ThereIsDatabaseError_Expect_FailedToGetCapabilitiesToBeThrown() throws SQLException {
        when(databaseConnectorMock.getConnection()).thenThrow(new SQLException());
        assertThatExceptionOfType(FailedToGetCapabilitiesException.class).isThrownBy(()->capabilityService.getCapabilities());
    }

}

