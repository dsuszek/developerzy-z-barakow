package org.kainos.ea.service;

import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.PasswordEncoder;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.model.LoginResponse;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public LoginResponse login(LoginRequest login) throws NoSuchAlgorithmException, FailedToGenerateTokenException, FailedToLoginException {
        try {
            String hashedPasswordFromDB = authDao.getPasswordFromDatabase(login);
            if (hashedPasswordFromDB == null) {
                throw new FailedToLoginException();
            }
            if (PasswordEncoder.checkPassword(PasswordEncoder.encodePassword(login.getPassword()), hashedPasswordFromDB)) {
                String token = authDao.generateToken(login.getEmail());
                 int roleId = authDao.getRoleIDFromEmail(login.getEmail());
                return new LoginResponse(token,roleId==2);
            } else {
                throw new FailedToLoginException();
            }
        } catch (SQLException e) {
            throw new FailedToGenerateTokenException();
        }
    }
}
