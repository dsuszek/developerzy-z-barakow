package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.service.JobRoleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "Job Roles")
@Path("/api")
public class JobRoleController {
    private final JobRoleService jobRoleService = new JobRoleService(new JobRoleDao(), new JobRoleValidator());
    private final static Logger logger = LoggerFactory.getLogger(JobRoleService.class);

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        try {
            return Response.ok(jobRoleService.getJobRoles()).build();
        } catch (FailedToGetJobRolesException e) {
            logger.error("Roles failed to be found! Error: {}", e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRoleById(@PathParam("id") int id) {
        try {
            return Response.ok(jobRoleService.findRoleById(id)).build();
        } catch (JobRoleDoesNotExistException e) {
            logger.error("Failed to get a role! Error: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (FailedToGetJobRoleException e) {
            logger.error("Role does not exist! Id: {} Error: {}", id, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
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

    @DELETE
    @Path("/admin/job-role/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("id") short id) {
        try {
            jobRoleService.deleteJobRole(id);

            return Response.ok().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteJobRoleException e) {
            return Response.serverError().build();
        }
    }
}