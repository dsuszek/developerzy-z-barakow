package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {
    private final BandDao bandDao = Mockito.mock(BandDao.class);
    private final BandValidator bandValidator = Mockito.mock(BandValidator.class);
    private BandService bandService = new BandService(bandDao, bandValidator);

    @Test
    public void createBand_WhenBandDataIsIncorrect_ShouldThrowFailedToCreateBandException() throws SQLException, FailedToCreateBandException {
        // given
        BandRequest bandRequest = new BandRequest("Trainee", (short) -42);
        // when
        when(bandDao.createBand(bandRequest)).thenThrow(new FailedToCreateBandException());
        // then
        assertThatExceptionOfType(FailedToCreateBandException.class)
                .isThrownBy(() -> bandService.createBand(bandRequest));
    }

    @Test
    public void createBand_WhenBandDataIsCorrect_ShouldReturnNewBand() throws SQLException, FailedToCreateBandException {
        // given
        BandRequest bandRequest = new BandRequest("Trainee", (short) 1);
        // when
        when(bandDao.createBand(bandRequest)).thenReturn(new Band(
                (short) 1,
                "Trainee",
                (short) 1
        ));
    }
}
