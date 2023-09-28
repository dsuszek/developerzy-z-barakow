package org.kainos.ea.db;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.exception.TokenExpiredException;
import org.kainos.ea.model.LoginRequest;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;
import java.util.UUID;


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
        String token = UUID.randomUUID().toString();
        java.util.Date expiry = DateUtils.addHours(new Date(), 1);

        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Token (email, token, expiry) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement);
        st.setString(1, email);
        st.setString(2, token);
        st.setTimestamp(3, new Timestamp(expiry.getTime()));

        st.executeUpdate();

        return token;
    }

    public int getRoleIDFromToken(String token) throws SQLException, TokenExpiredException {
        Connection c = databaseConnector.getConnection();
        String selectStatement = "SELECT roleId, expiry FROM Users JOIN Token using (email) where Token = ? ;";
        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setString(1, token);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Timestamp expiry = rs.getTimestamp("Expiry");
            if (expiry.after(new Date())) {
                return rs.getInt("roleId");
            } else {
                throw new TokenExpiredException();
            }
        }

        return -1;
    }


}
