package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.BandRequest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandValidatorTest {

    @Mock
    BandRequest band;
    BandValidator bandValidator = new BandValidator();

    @Test
    void When_BandLevelSmallerThan0_Expect_MessageReturned() {
        when(band.getLevel()).thenReturn((short) -1);
        assertThat(bandValidator.isValidBand(band)).isEqualTo("Band level out of boundaries 0-9");
    }

    @Test
    void When_BandLevelGreaterThan9_Expect_MessageReturned() {
        when(band.getLevel()).thenReturn((short) 10);
        assertThat(bandValidator.isValidBand(band)).isEqualTo("Band level out of boundaries 0-9");
    }

    @Test
    void When_BandLevelWithinBoundaries_Expect_NoMessageReturned() {
        when(band.getLevel()).thenReturn((short) 6);
        assertThat(bandValidator.isValidBand(band)).isEqualTo(null);
    }
}