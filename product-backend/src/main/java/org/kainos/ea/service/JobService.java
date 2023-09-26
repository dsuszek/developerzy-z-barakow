package org.kainos.ea.service;

import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.model.JobRole;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

public class JobService {
    public JobDao jobDao;
    public DatabaseConnector databaseConnector;
    public JobService(JobDao jobDao, DatabaseConnector databaseConnector) {
        this.jobDao = jobDao;
        this.databaseConnector = databaseConnector;
    }
    public List<JobRole> getJobRoles() throws SQLException {
        return jobDao.getRoles(databaseConnector.getConnection());
    }
}




