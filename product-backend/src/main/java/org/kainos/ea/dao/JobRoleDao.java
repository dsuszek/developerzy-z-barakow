package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRoleDao {

    public List<JobRole> getRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT * FROM JobRoles;");
        List<JobRole> jobRoles = new ArrayList<>();
        while (rs.next()) {
            JobRole jobRole = new JobRole(
                    rs.getShort("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("link"),
                    rs.getShort("bandId")
            );
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public Optional<JobRole> findRoleById(int id, Connection c) throws SQLException {
        String queryStatement = "SELECT id, name, description, link" +
                " FROM JobRoles where id = ?;";
        PreparedStatement st = c.prepareStatement(queryStatement);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return Optional.of(new JobRole(
                    rs.getShort("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("link"),
                    rs.getShort("bandId")
            ));
        }
        return Optional.empty();
    }

    public JobRole createJobRole(JobRoleRequest jobRole) throws SQLException, FailedToCreateJobRoleException {

        DatabaseConnector databaseConnector = new DatabaseConnector();

        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `JobRoles` (name, description, link, bandId) VALUES (?, ?, ?, ?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getDescription());
        st.setString(3, jobRole.getLink());
        st.setShort(4, jobRole.getBandId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (!rs.next()) {
            throw new FailedToCreateJobRoleException();
        }
        return new JobRole
                (rs.getShort(1),
                        jobRole.getName(),
                        jobRole.getDescription(),
                        jobRole.getLink(),
                        jobRole.getBandId());
    }
}
