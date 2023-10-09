package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.CapabilityRequest;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.CapabilityValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Tag(name = "Capabilities API")
@Path("/api")
public class CapabilityController {
    CapabilityService capabilityService;
    public CapabilityController() throws SQLException {
        this.capabilityService = new CapabilityService(new CapabilityDao(), new DatabaseConnector(), new CapabilityValidator());
    }
    private final static Logger logger = LoggerFactory.getLogger(CapabilityService.class);
    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities() throws SQLException, CapabilityDoesNotExistException, FailedToGetCapabilitiesException {
        try {
            return Response.ok(capabilityService.getCapabilities()).build();
        } catch (FailedToGetCapabilitiesException e) {
            logger.error("Capabilities failed to be found! Error: {}", e.getMessage());
            return Response.serverError().build();
        }
    }
    @POST
    @Path("/capabilities")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCapability(CapabilityRequest capability){
        try{
            return Response.ok(capabilityService.createCapability(capability)).build();
        }catch(FailedToCreateNewCapabilityException e){
            logger.error("FailedtocreateCapability!Error:{}",e.getMessage());
            return Response.serverError().entity(new ErrorResponse(e.getMessage())).build();
        }catch(InvalidCapabilityException e){
            logger.error("InvalidCapabilitydata!Error:{}",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

}