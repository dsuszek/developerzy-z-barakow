package org.kainos.ea.service;

import org.kainos.ea.db.AuthDao;
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
        if (authDao.validLoginRequest(login)) {
            try {
                return authDao.generateToken(login.getEmail());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }
}
