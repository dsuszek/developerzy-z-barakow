package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobDao {
    DatabaseConnector conn = new DatabaseConnector();
    Connection c = conn.getConnection();

    public JobDao() throws SQLException {
    }
    public List<JobRole> getRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT * FROM JobRoles;");
        List<JobRole> jobRoles = new ArrayList<>();
        while ((rs.next())
        ) {
            JobRole jobRole = new JobRole(
                    rs.getShort("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("link"),
                    rs.getShort("bandId"),
                    rs.getShort("capabilityId")
            );
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public Optional<JobRoleDetails> findRoleById(int id, Connection c) throws SQLException {
        String queryStatement = "SELECT j.id, j.name AS \"name\", j.description, j.link, b.name AS \"bandName\", b.level AS \"bandLevel\", c.capabilityName FROM JobRoles j INNER JOIN Bands b ON j.bandId = b.id INNER JOIN Capabilities c ON c.id = j.capabilityId WHERE j.id = ?;";
        PreparedStatement st = c.prepareStatement(queryStatement);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return Optional.of(new JobRoleDetails(
                    rs.getShort("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("link"),
                    rs.getString("bandName"),
                    rs.getShort("bandLevel"),
                    rs.getString("capabilityName")
            ));
        }
        return Optional.empty();
    }
}