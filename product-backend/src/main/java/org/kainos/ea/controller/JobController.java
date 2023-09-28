package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.kainos.ea.dao.JobDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToGetRoleException;
import org.kainos.ea.exception.FailedToGetRolesException;
import org.kainos.ea.exception.RoleDoesNotExistException;
import org.kainos.ea.service.JobService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


@Tag(name = "API for JOBS app")
@Path("/api")
public class JobController {
    private static JobService jobService;

    static {
        try {
            jobService = new JobService(new JobDao(), new DatabaseConnector());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(JobService.class);


    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() throws SQLException, RoleDoesNotExistException, FailedToGetRoleException {
        try {
            return Response.ok(jobService.getJobRoles()).build();
                } catch (FailedToGetRolesException e) {
            logger.error("Roles failed to be found! Error: {}", e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRoleById(@PathParam("id") int id) throws SQLException {
        try {
            return Response.ok(jobService.findRoleById(id)).build();
        } catch (RoleDoesNotExistException e) {
            logger.error("Failed to get a role! Error: {}", e.getMessage());

            return Response.serverError().build();
        } catch (FailedToGetRoleException e) {
            logger.error("Role does not exist! Id: {} Error: {}", id, e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
