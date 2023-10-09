package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateNewCapabilityException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.CapabilityRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {
    public CapabilityDao() throws SQLException {
    }

    public List<Capability> getCapabilities(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT * FROM Capabilities;");
        List<Capability> capabilities = new ArrayList<>();
        while ((rs.next())
        ) {
            Capability capability = new Capability(
                    rs.getShort("id"),
                    rs.getString("capabilityName"),
                    rs.getString("leadName"),
                    rs.getString("capabilityLeadPicture"),
                    rs.getString("message")
            );
            capability.setId(rs.getShort("id"));
            capabilities.add(capability);
        }
        return capabilities;
    }

    public int createCapability(CapabilityRequest capability) throws SQLException, FailedToCreateNewCapabilityException {
        Connection c = new DatabaseConnector().getConnection();
        String insertStatement = "INSERT INTO Capabilities(capabilityName,leadName,capabilityLeadPicture,message)" +
                "VALUES(?,?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, capability.getCapabilityName());
        st.setString(2, capability.getLeadName());
        st.setString(3, capability.getCapabilityLeadPicture());
        st.setString(4, capability.getMessage());
        int affectedRows = st.executeUpdate();
        if (affectedRows == 0) {
            throw new FailedToCreateNewCapabilityException();
        }
        int capNo = 0;
        try (ResultSet rs=st.getGeneratedKeys()){
            if (rs.next()) {
                capNo = rs.getShort(1);
            }
        }
        return capNo;
    }
}