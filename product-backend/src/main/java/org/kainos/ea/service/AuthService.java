package org.kainos.ea.service;

import org.kainos.ea.db.PasswordEncoder;
import org.kainos.ea.db.UserDao;
import org.kainos.ea.exception.FailedToRegisterUserException;
import org.kainos.ea.exception.InvalidUserRegistrationRequestException;
import org.kainos.ea.model.User;
import org.kainos.ea.model.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthService {
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final UserDao userDao;
    private final UserRegistrationValidator userRegistrationValidator;

    public AuthService(UserDao userDao, UserRegistrationValidator userRegistrationValidator) {
        this.userDao = userDao;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    public User registerUser(UserRegistrationRequest userRegistrationRequest) throws InvalidUserRegistrationRequestException, FailedToRegisterUserException {
        try {
            String validation = userRegistrationValidator.isValidUserRegistrationRequest(userRegistrationRequest);

            if (validation != null) {
                throw new InvalidUserRegistrationRequestException();
            }

            // After checking the correctness of password, assign encoded version to this request
            userRegistrationRequest.setPassword(PasswordEncoder.encodePassword(userRegistrationRequest.getPassword()));

            return userDao.registerUser(userRegistrationRequest);
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());

            throw new FailedToRegisterUserException();
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm exception! Error: {}", e.getMessage());

            throw new RuntimeException(e);
        }
    }
}
