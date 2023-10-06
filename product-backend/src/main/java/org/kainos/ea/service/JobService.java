package org.kainos.ea.service;

import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToGetRoleException;
import org.kainos.ea.exception.FailedToGetRolesException;
import org.kainos.ea.exception.RoleDoesNotExistException;
import org.kainos.ea.model.JobRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class JobService {
    public JobDao jobDao;
    private final static Logger logger = LoggerFactory.getLogger(JobService.class);
    public DatabaseConnector databaseConnector;

    public JobService(JobDao jobDao, DatabaseConnector databaseConnector) {
        this.jobDao = jobDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getJobRoles() throws FailedToGetRolesException {
        try {
            return jobDao.getRoles(databaseConnector.getConnection());
        }catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());
            throw new FailedToGetRolesException();
        }
    }

    public JobRole findRoleById(int id) throws RoleDoesNotExistException, FailedToGetRoleException {
        try {
            Optional<JobRole> jobRole = jobDao.findRoleById(id,databaseConnector.getConnection());
            return jobRole.orElseThrow(RoleDoesNotExistException::new);
        } catch ( SQLException e) {
            throw new FailedToGetRoleException();
        }
    }
}