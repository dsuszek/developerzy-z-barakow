package org.kainos.ea.service;

import org.kainos.ea.dao.UserDao;
import org.kainos.ea.model.UserRegistrationRequest;
import java.util.regex.Pattern;

public class UserRegistrationValidator {
    private static final Pattern EMAIL_DOMAIN_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@kainos\\.com$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,50}$)[A-Za-z0-9+_.-]+@kainos\\.com$");
    private static final Pattern PASSWORD_SPECIAL_CHARS_PATTERN = Pattern.compile(".*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\-].*");
    private static final Pattern PASSWORD_UPPERCASE_LETTER_PATTERN = Pattern.compile("(?=.*[A-Z]).*");
    private static final Pattern PASSWORD_LOWERCASE_LETTER_PATTERN = Pattern.compile("(?=.*[a-z]).*");

    private final UserDao userDao = new UserDao();

    public String isValidUserRegistrationRequest(UserRegistrationRequest userRegistration) {
        if (!EMAIL_DOMAIN_PATTERN.matcher(userRegistration.getEmail()).matches()) {
            return "Email does not have @kainos.com domain";
        }
        if (!EMAIL_PATTERN.matcher(userRegistration.getEmail()).matches()) {
            return "Email address must have 50 characters at the maximum";
        }
        if (!PASSWORD_SPECIAL_CHARS_PATTERN.matcher(userRegistration.getPassword()).matches()) {
            return "Password must contain at least one special character";
        }
        if (!PASSWORD_UPPERCASE_LETTER_PATTERN.matcher(userRegistration.getPassword()).matches()) {
            return "Password must contain at least one uppercase letter";
        }
        if (!PASSWORD_LOWERCASE_LETTER_PATTERN.matcher(userRegistration.getPassword()).matches()) {
            return "Password must contain at least one lowercase letter";
        }

        // checks the length of password
        if (userRegistration.getPassword().length() < 9) {
            return "Password must contain at least 9 characters";
        }
        if (userRegistration.getPassword().length() >= 50) {
            return "Password must contain 50 characters at the maximum";
        }

        return null;
    }
}
