package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToGetRoleException;
import org.kainos.ea.exception.FailedToGetRolesException;
import org.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    rs.getString("link")
            );
            jobRole.setId(rs.getShort("id"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public Optional<JobRole> findRoleById(int id, Connection c) throws SQLException {

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, name, description, link" +
                " FROM JobRoles where id=" + id);

        while (rs.next()) {
            return Optional.of(new JobRole(
                    rs.getShort("Id"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Link")
            ));

        }
        return Optional.empty();
    }
}