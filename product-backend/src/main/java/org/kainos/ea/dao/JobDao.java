package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {
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
                    rs.getString("link")
            );
            jobRole.setId(rs.getShort("id"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }
}
