package org.kainos.ea.dao;

import org.kainos.ea.model.Capability;

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
}