package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.ErrorResponse;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.service.BandService;
import org.kainos.ea.service.BandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;

@Tag(name = "Band API")
@Path("/api")
public class BandController {
    private final BandService bandService = new BandService(new BandDao(), new BandValidator());
    private final static Logger logger = LoggerFactory.getLogger(BandService.class);

    @GET
    @Path("/admin/band")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands() {
        try {
            return Response.ok(bandService.getBands()).build();
        } catch (FailedToGetBandsException e) {
            logger.error("Failed to get bands! Error: {}", e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/admin/band")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBand(BandRequest band) {
        try {
            return Response.ok(bandService.createBand(band)).build();
        } catch (FailedToCreateBandException e) {
            logger.error("Failed to create band! Error: {}", e.getMessage());

            return Response.serverError().entity(new ErrorResponse(e.getMessage())).build();
        } catch (InvalidBandException e) {
            logger.error("Invalid band data! Error: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
}