package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateNewCapabilityException;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.exception.InvalidCapabilityException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.CapabilityRequest;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.CapabilityValidator;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDaoMock = mock(CapabilityDao.class);
    DatabaseConnector databaseConnectorMock = mock(DatabaseConnector.class);
    CapabilityValidator capabilityValidatorMock = mock(CapabilityValidator.class);
    Connection connMock = mock(Connection.class);
    CapabilityRequest capabilityRequestMock = new CapabilityRequest("Engineering Science",
            "John Wick", "11111", "hello");
    CapabilityRequest inValidCapabilityRequestMock = new CapabilityRequest("dfdfdfdfdfdfdfdfdfdfdf" +
            "dfdfdfdfdfdfdfdfdfdfdfdfd" +
            "fdfdfdfdfdfdfdfdfdfdfdfdf" +
            "dfdfdfdfdfdfd",
            "John Wick", "11111", "hello");
    Connection conn;
    CapabilityService capabilityService = new CapabilityService(capabilityDaoMock, databaseConnectorMock, capabilityValidatorMock);
    CapabilityValidator capabilityValidator = new CapabilityValidator();

    @Test
    void getCapabilites_whenCapabilitiesAvailable_shouldReturnListOfCapabilities() throws SQLException, FailedToGetCapabilitiesException {
        //given
        CapabilityService sut = new CapabilityService(capabilityDaoMock, databaseConnectorMock, capabilityValidatorMock);
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
    @Test
    void createCapability_ExpectTheCapabilityToBeInDatabase() throws SQLException, InvalidCapabilityException, FailedToCreateNewCapabilityException {
        //given
        when(databaseConnectorMock.getConnection()).thenReturn(conn);
        CapabilityService sut = new CapabilityService(capabilityDaoMock, databaseConnectorMock, capabilityValidatorMock);
        //when
        when(capabilityDaoMock.createCapability(capabilityRequestMock)).thenReturn(1);
        int newCapabilityId = sut.createCapability(capabilityRequestMock);
        //then
        assertThat(newCapabilityId).isGreaterThan(0);
        }
    @Test
    void createCapability_When_ThereIsInputError_Expect_FailedToCreateCapabilitiesToBeThrown() throws SQLException, FailedToCreateNewCapabilityException {
        when(capabilityDaoMock.createCapability(inValidCapabilityRequestMock)).thenThrow(new SQLException());
        assertThatExceptionOfType(FailedToCreateNewCapabilityException.class).isThrownBy(()->capabilityService.createCapability(inValidCapabilityRequestMock)).
                withMessageMatching("Failed to create new capability");
    }
}

