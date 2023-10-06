package org.kainos.ea.dao;

import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Capability> findCapabilityById(int id, Connection c) throws SQLException {

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, capabilityName, leadName, capabilityLeadPicture, message" +
                " FROM Capabilities where id=" + id);

        while (rs.next()) {
            return Optional.of(new Capability(
                    rs.getShort("id"),
                    rs.getString("capabilityName"),
                    rs.getString("leadName"),
                    rs.getString("capabilityLeadPicture"),
                    rs.getString("message")
            ));
        }
        return Optional.empty();
    }
}