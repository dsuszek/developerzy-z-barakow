package org.kainos.ea.service;

import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.db.PasswordEncoder;
import org.kainos.ea.db.UserDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.model.User;
import org.kainos.ea.model.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthService {
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final AuthDao authDao;
    private UserDao userDao;
    private UserRegistrationValidator userRegistrationValidator;

    public AuthService(UserDao userDao, AuthDao authDao, UserRegistrationValidator userRegistrationValidator) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    public User registerUser(UserRegistrationRequest userRegistration) throws InvalidUserRegistrationRequestException, FailedToRegisterUserException {
        try {
            String validation = userRegistrationValidator.isValidUserRegistrationRequest(userRegistration);

            if (validation != null) {
                throw new InvalidUserRegistrationRequestException(validation);
            }

            if (userDao.isEmailTaken(userRegistration.getEmail())) {
                throw new FailedToRegisterUserException();
            }

            // After checking the correctness of password, assign encoded version to this request
            userRegistration.setPassword(PasswordEncoder.encodePassword(userRegistration.getPassword()));

            return userDao.registerUser(userRegistration);
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());

            throw new FailedToRegisterUserException();
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm exception! Error: {}", e.getMessage());

            throw new RuntimeException(e);
        }
    }

    public String login(LoginRequest login) throws NoSuchAlgorithmException, FailedToGenerateTokenException, FailedToLoginException {
        try {
            String hashedPasswordFromDB = authDao.getPasswordFromDatabase(login);
            if (hashedPasswordFromDB == null) {
                throw new FailedToLoginException();
            }
            if (PasswordEncoder.checkPassword(PasswordEncoder.encodePassword(login.getPassword()), hashedPasswordFromDB)) {
                return authDao.generateToken(login.getEmail());
            } else {
                throw new FailedToLoginException();
            }
        } catch (SQLException e) {
            throw new FailedToGenerateTokenException();
        }
    }
}
