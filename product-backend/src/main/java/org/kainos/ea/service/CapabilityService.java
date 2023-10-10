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
    public CapabilityValidator capabilityValidator;
    private final static Logger logger = LoggerFactory.getLogger(CapabilityService.class);

    public CapabilityService(CapabilityDao capabilityDao, DatabaseConnector databaseConnector, CapabilityValidator capabilityValidator) {
        this.capabilityDao = capabilityDao;
        this.databaseConnector = databaseConnector;
        this.capabilityValidator = capabilityValidator;
    }

    public List<Capability> getCapabilities() throws FailedToGetCapabilitiesException {
        try {
            return capabilityDao.getCapabilities(databaseConnector.getConnection());
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());
            throw new FailedToGetCapabilitiesException();
        }
    }
    public int createCapability(CapabilityRequest capability)throws InvalidCapabilityException,FailedToCreateNewCapabilityException{
        try{
            String validation = capabilityValidator.isCapabilityValid(capability);
            if(validation!=null){
                throw new InvalidCapabilityException(validation);
            }
            return capabilityDao.createCapability(capability);
        }catch(InvalidCapabilityException | SQLException e){
            throw new FailedToCreateNewCapabilityException();
        }
    }
}