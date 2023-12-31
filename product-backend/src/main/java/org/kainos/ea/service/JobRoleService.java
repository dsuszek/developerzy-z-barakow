package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleDetails;
import org.kainos.ea.model.JobRoleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JobRoleService {

    private final static Logger logger = LoggerFactory.getLogger(JobRoleService.class);
    private final JobRoleDao jobRoleDao;
    private DatabaseConnector databaseConnector;
    private JobRoleValidator jobRoleValidator;

    public JobRoleService(JobRoleDao jobRoleDao, JobRoleValidator jobRoleValidator) {
        this.jobRoleDao = jobRoleDao;
        this.jobRoleValidator = jobRoleValidator;
    }

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }

    public JobRole createJobRole(JobRoleRequest jobRole) throws FailedToCreateJobRoleException, InvalidJobRoleException {
        try {
            String validation = jobRoleValidator.isValidJobRole(jobRole);

            if (validation != null) {
                throw new InvalidJobRoleException();
            }

            return jobRoleDao.createJobRole(jobRole);
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());

            throw new FailedToCreateJobRoleException();
        }
    }
    public List<JobRole> getJobRoles() throws FailedToGetRolesException {
        try {
            return jobRoleDao.getRoles(databaseConnector.getConnection());
        }catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());
            throw new FailedToGetRolesException();
        }
    }

    public JobRoleDetails findRoleById(int id) throws RoleDoesNotExistException, FailedToGetRoleException {
        try {
            Optional<JobRoleDetails> jobRole = jobRoleDao.findRoleById(id, databaseConnector.getConnection());
            return jobRole.orElseThrow(RoleDoesNotExistException::new);
        } catch ( SQLException e) {
            throw new FailedToGetRoleException();
        }
    }

    public void deleteJobRole(short id) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            Optional<JobRole> jobRoleToBeDeleted = jobRoleDao.getJobRoleById(id);
            if (jobRoleToBeDeleted.isEmpty()) {
                throw new JobRoleDoesNotExistException();
            }

            jobRoleDao.deleteJobRole(id);
        } catch (SQLException e) {
            throw new FailedToDeleteJobRoleException();
        }
    }
}
