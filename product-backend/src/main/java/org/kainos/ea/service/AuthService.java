package org.kainos.ea.service;

import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.PasswordEncoder;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
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
