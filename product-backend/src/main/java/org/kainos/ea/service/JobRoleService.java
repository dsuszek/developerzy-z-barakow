package org.kainos.ea.service;

import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.FailedToCreateProductException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;

public class JobRoleService {

    private final static Logger logger = LoggerFactory.getLogger(JobRoleService.class);
    private final JobRoleDao jobRoleDao;
    private final JobRoleValidator jobRoleValidator;

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
}
