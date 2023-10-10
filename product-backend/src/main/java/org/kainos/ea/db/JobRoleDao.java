package org.kainos.ea.db;

import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;

import java.sql.*;
import java.util.Optional;

public class JobRoleDao {
    public Optional<JobRole> getJobRoleById(short id) throws SQLException {

        Connection c = DatabaseConnector.getConnection();
        String selectStatement = "SELECT * FROM `JobRoles` WHERE id = ?;";
        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
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

    public void deleteJobRole(short id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM `JobRoles` WHERE id = ?;";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, id);
        st.executeUpdate();
    }


    public JobRole createJobRole(JobRoleRequest jobRole) throws SQLException, FailedToCreateJobRoleException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO `JobRoles` (name, description, link, bandId) VALUES (?, ?, ?, ?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getDescription());
        st.setString(3, jobRole.getLink());
        st.setShort(4, jobRole.getBandId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (!rs.next()) {
            throw new FailedToCreateJobRoleException("No job role id has been returned");
        }
        return new JobRole
                (rs.getShort(1),
                        jobRole.getName(),
                        jobRole.getDescription(),
                        jobRole.getLink(),
                        jobRole.getBandId());
    }
}
