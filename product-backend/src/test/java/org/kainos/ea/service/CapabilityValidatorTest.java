package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.kainos.ea.model.CapabilityRequest;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class CapabilityValidatorTest {
    CapabilityRequest inValidCapabilityRequestMock = new CapabilityRequest("dfdfdfdfdfdfdfdfdfdfdf" +
            "dfdfdfdfdfdfdfdfdfdfdfdfd" +
            "fdfdfdfdfdfdfdfdfdfdfdfdf" +
            "dfdfdfdfdfdfd",
            "John Wick", "11111", "hello");
    CapabilityValidator capabilityValidator = new CapabilityValidator();
    @Test
    void createCapability_WhenTheDataIsInvalid_Expect_FailedToCreateCapabilitiesToBeThrown() throws SQLException {
        assertThat(capabilityValidator.isCapabilityValid(inValidCapabilityRequestMock)).isEqualTo("Capability name longer than 50 chars");
    }
}
