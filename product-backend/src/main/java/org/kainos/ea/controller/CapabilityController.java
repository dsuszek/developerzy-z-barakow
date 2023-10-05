package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Tag(name = "API for capabilities")
@Path("/api")
public class CapabilityController {
    CapabilityService capabilityService;
    public CapabilityController() throws SQLException {
        this.capabilityService = new CapabilityService(new CapabilityDao(), new DatabaseConnector());
    }
    private final static Logger logger = LoggerFactory.getLogger(CapabilityService.class);
    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities() throws SQLException, CapabilityDoesNotExistException, FailedToGetCapabilityException {
        try {
            return Response.ok(capabilityService.getCapabilities()).build();
        } catch (FailedToGetCapabilityException e) {
            logger.error("Capabilities failed to be found! Error: {}", e.getMessage());
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/capabilities/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCapabilityById(@PathParam("id") int id) {
        try {
            return Response.ok(capabilityService.findCapabilityById(id)).build();
        } catch (CapabilityDoesNotExistException e) {
            logger.error("Failed to get a capability! Error: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (FailedToGetCapabilityException e) {
            logger.error("Capability does not exist! Id: {} Error: {}", id, e.getMessage());
            return Response.serverError().build();
        }
    }
}