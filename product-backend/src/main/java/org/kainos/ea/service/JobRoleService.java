package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JobRoleService {

    private final static Logger logger = LoggerFactory.getLogger(JobRoleService.class);
    private final JobRoleDao jobRoleDao;
    private final JobRoleValidator jobRoleValidator;
    private DatabaseConnector databaseConnector;

    public JobRoleService(JobRoleDao jobRoleDao, JobRoleValidator jobRoleValidator) {
        this.jobRoleDao = jobRoleDao;
        this.jobRoleValidator = jobRoleValidator;
    }

    public JobRole createJobRole(JobRoleRequest jobRole) throws FailedToCreateJobRoleException, InvalidJobRoleException {
        try {
            String validation = jobRoleValidator.isValidJobRole(jobRole);

            if (validation != null) {
                throw new InvalidJobRoleException(validation);
            }

            return jobRoleDao.createJobRole(jobRole);
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());

            throw new FailedToCreateJobRoleException(e.getMessage());
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

    public JobRole findRoleById(int id) throws RoleDoesNotExistException, FailedToGetRoleException {
        try {
            Optional<JobRole> jobRole = jobRoleDao.findRoleById(id,databaseConnector.getConnection());
            return jobRole.orElseThrow(RoleDoesNotExistException::new);
        } catch ( SQLException e) {
            throw new FailedToGetRoleException();
        }
    }
}
