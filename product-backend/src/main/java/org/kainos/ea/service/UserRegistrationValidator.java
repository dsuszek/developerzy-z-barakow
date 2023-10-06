package org.kainos.ea.service;

import org.kainos.ea.db.UserDao;
import org.kainos.ea.exception.FailedToRegisterUserException;
import org.kainos.ea.model.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserRegistrationValidator {
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);



    private static final Pattern EMAIL_DOMAIN_PATTERN = Pattern.compile("/@kainos\\.com$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,50}$)[A-Za-z0-9+_.-]+@kainos\\.com$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).{9,}$");
    private static final Pattern PASSWORD_SPECIAL_CHARS_PATTERN = Pattern.compile(".*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\-].*");
    private static final Pattern PASSWORD_UPPERCASE_LETTER_PATTERN = Pattern.compile("(?=.*[A-Z]).*");
    private static final Pattern PASSWORD_LOWERCASE_LETTER_PATTERN = Pattern.compile("(?=.*[a-z]).*");
    private static final Pattern PASSWORD_LENGTH_PATTERN = Pattern.compile("(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).{9,50}");

    private UserDao userDao = new UserDao();

    public String isValidUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        if (!EMAIL_DOMAIN_PATTERN.matcher(userRegistrationRequest.getEmail()).find()) {
            return "Email does not have @kainos.com domain";
        }
        if (!EMAIL_PATTERN.matcher(userRegistrationRequest.getEmail()).matches()) {
            return "Email address must have 50 characters at the maximum";
        }
        if (!PASSWORD_SPECIAL_CHARS_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            return "Password must contain at least one special character";
        }
        if (!PASSWORD_UPPERCASE_LETTER_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            return "Password must contain at least one uppercase letter";
        }
        if (!PASSWORD_LOWERCASE_LETTER_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            return "Password must contain at least one lowercase letter";
        }
        if (!PASSWORD_LENGTH_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            return "Password must contain between 9 and 50 characters";
        }

        try {
            if (userDao.isEmailTaken(userRegistrationRequest.getEmail())) {
                return "Email address already in use";
            }
        } catch (SQLException e) {
            logger.error("Error: {}", e.getMessage());
        }

        return null;
    }
}
