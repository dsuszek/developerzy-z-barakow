package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.UserRegistrationRequest;
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
    void When_UserEmailInvalidDomain_Expect_MessageReturned() {
        when(userRegistration.getEmail()).thenReturn("xyz@gmail.com");
        assertThat(userRegistrationValidator.isValidUserRegistrationRequest(userRegistration)).isEqualTo("Email does not have @kainos.com domain");
    }

    @Test
    void When_UserEmailTooLong_Expect_MessageReturned() {
        when(userRegistration.getEmail()).thenReturn("fngewrhgeirhgiegkjergnreghrehgrengjerhgiueggihgeiuhghewbfiewgiuewhgifegi@kainos.com");
        assertThat(userRegistrationValidator.isValidUserRegistrationRequest(userRegistration)).isEqualTo("Email address must have 50 characters at the maximum");
    }
}
