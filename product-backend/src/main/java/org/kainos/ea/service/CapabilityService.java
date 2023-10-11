package org.kainos.ea.service;

import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.CapabilityRequest;
import org.kainos.ea.model.JobRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CapabilityService {
    public CapabilityDao capabilityDao;
    public DatabaseConnector databaseConnector;
    private final static Logger logger = LoggerFactory.getLogger(CapabilityService.class);

    public CapabilityService(CapabilityDao capabilityDao, DatabaseConnector databaseConnector) {
        this.capabilityDao = capabilityDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Capability> getCapabilities() throws FailedToGetCapabilityException {
        try {
            return capabilityDao.getCapabilities(databaseConnector.getConnection());
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());
            throw new FailedToGetCapabilityException();
        }
    }
}