package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.UserRegistrationRequest;
import org.kainos.ea.service.UserRegistrationValidator;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationValidatorTest {
    @Mock
    UserRegistrationRequest userRegistration;

    UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();

    @Test
    void When_UserEmailInvalid_Expect_MessageReturned() {
        when(userRegistration.getEmail()).thenReturn("xyz@gmail.com");
        assertThat(userRegistrationValidator.isValidUserRegistrationRequest(userRegistration)).isEqualTo("Invalid email address");
    }
}
