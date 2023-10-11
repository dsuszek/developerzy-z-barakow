package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {
    private final BandDao bandDaoMock = Mockito.mock(BandDao.class);
    private final BandValidator bandValidatorMock = Mockito.mock(BandValidator.class);
    private final DatabaseConnector databaseConnectorMock = Mockito.mock(DatabaseConnector.class);
    private Connection conn;

    @Test
    public void createBand_WhenBandDataIsIncorrect_ShouldThrowFailedToCreateBandException() throws SQLException, FailedToCreateBandException {
        // given
        BandService bandServiceMock = new BandService(bandDaoMock, bandValidatorMock);
        BandRequest bandRequest = new BandRequest("Trainee", (short) -42);
        // when
        when(bandDaoMock.createBand(bandRequest)).thenThrow(new FailedToCreateBandException());
        // then
        assertThatExceptionOfType(FailedToCreateBandException.class)
                .isThrownBy(() -> bandServiceMock.createBand(bandRequest));
    }

    @Test
    public void createBand_WhenBandDataIsCorrect_ShouldReturnNewBand() throws SQLException, FailedToCreateBandException {


    }
}
