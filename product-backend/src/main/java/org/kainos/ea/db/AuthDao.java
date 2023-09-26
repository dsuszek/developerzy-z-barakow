package org.kainos.ea.db;

import org.kainos.ea.model.LoginRequest;

import javax.swing.plaf.nimbus.State;
import java.sql.*;


public class AuthDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean validLoginRequest(LoginRequest loginRequest) {
        try (Connection c = databaseConnector.getConnection()) {
            PreparedStatement st = c.prepareStatement("SELECT Password FROM Users u WHERE u.email = ?;");
            st.setString(1,loginRequest.getEmail());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getString("password").equals(loginRequest.getPassword());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


}
