package org.kainos.ea.db;

import org.kainos.ea.exception.FailedToRegisterUserException;
import org.kainos.ea.model.User;
import org.kainos.ea.model.UserRegistrationRequest;
import java.sql.*;

public class UserDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    // Checks if user's email address is already in use
    public boolean isEmailTaken(String email) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String selectStatement = "SELECT 1 FROM `Users` WHERE email = ? LIMIT 1;";
        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            return true;
        }

        return false;
    }

    public User registerUser(UserRegistrationRequest user) throws SQLException, FailedToRegisterUserException {
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO `Users` (email, password, roleId) VALUES (?, ?, ?);";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, user.getEmail());
        st.setString(2, user.getPassword());
        st.setShort(3, user.getRoleId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (!rs.next()) {
            throw new FailedToRegisterUserException("No user id returned");
        }

        return new User
                (rs.getShort(1),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRoleId());
    }
}
