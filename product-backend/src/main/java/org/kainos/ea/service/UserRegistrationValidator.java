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
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@kainos\\.com$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])"
                                                                    + "(?=.*[a-z])(?=.*[A-Z])"
                                                                    + "(?=.*[@#$%^&+=])"
                                                                    + "(?=\\S+$).{8,}$");

    private UserDao userDao = new UserDao();

    public String isValidUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        if (!EMAIL_PATTERN.matcher(userRegistrationRequest.getEmail()).matches()) {
            return "Invalid email address";
        }

        if (!PASSWORD_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            return "Password doesn't meet all the criteria";
        }

        try {
            if (userDao.isEmailTaken(userRegistrationRequest.getEmail())) {
                return "Email address already in use";
            }
        } catch (SQLException e) {
            logger.error("Error: {}", e.getMessage());
        } catch (FailedToRegisterUserException e) {
            logger.error("Failed to register user! Error: {}", e.getMessage());
        }

        return null;
    }
}
