package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
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

    public JobRoleService(JobRoleDao jobRoleDao, JobRoleValidator jobRoleValidator) {
        this.jobRoleDao = jobRoleDao;
        this.jobRoleValidator = jobRoleValidator;
    }

    public List<JobRole> getJobRoles() throws FailedToGetJobRolesException {
        try {
            return jobRoleDao.getRoles();
        }catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public JobRole findRoleById(int id) throws JobRoleDoesNotExistException, FailedToGetJobRoleException {
        try {
            Optional<JobRole> jobRole = jobRoleDao.findRoleById(id);
            return jobRole.orElseThrow(JobRoleDoesNotExistException::new);
        } catch ( SQLException e) {
            throw new FailedToGetJobRoleException();
        }
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
