package org.kainos.ea.dao;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.model.LoginRequest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;

public class AuthDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public String getPasswordFromDatabase(LoginRequest loginRequest) throws NoSuchAlgorithmException {
        try (Connection c = databaseConnector.getConnection()) {
            String selectStatement = "SELECT `password` FROM `Users` WHERE email = ? ;";
            PreparedStatement st = c.prepareStatement(selectStatement);
            st.setString(1, loginRequest.getEmail());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String generateToken(String email) throws SQLException {
        return Jwts.builder()
                .claim("name", email)
                .claim("roleId", getRoleIDFromEmail(email))
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addHours(new Date(), 1))
                .signWith(
                        SignatureAlgorithm.HS256,
                        "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=".getBytes(StandardCharsets.UTF_8)
                )
                .compact();
    }

    private int getRoleIDFromEmail(String email) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String selectStatement = "SELECT roleId FROM Users where email = ? ;";
        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setString(1, email);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            return rs.getByte(1);
        }
        return -1;
    }
}
