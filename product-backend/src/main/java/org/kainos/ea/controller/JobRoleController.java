package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.service.JobRoleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Tag(name = "Job Roles")
@Path("/api")
public class JobRoleController {
    private JobRoleService jobRoleService = new JobRoleService(new JobRoleDao(), new JobRoleValidator());
    private final static Logger logger = LoggerFactory.getLogger(JobRoleService.class);

    @POST
    @Path("/admin/job-roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRole) {
        try {
            return Response.ok(jobRoleService.createJobRole(jobRole)).build();
        } catch (FailedToCreateJobRoleException e) {
            logger.error("Failed to create job role! Error: {}", e.getMessage());

            return Response.serverError().entity(new ErrorResponse(e.getMessage())).build();
        } catch (InvalidJobRoleException e) {
            logger.error("Invalid job role data! Error: {}", e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
}
