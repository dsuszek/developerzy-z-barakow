package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.db.UserDao;
import org.kainos.ea.exception.ErrorResponse;
import org.kainos.ea.exception.FailedToRegisterUserException;
import org.kainos.ea.exception.InvalidUserRegistrationRequestException;
import org.kainos.ea.model.UserRegistrationRequest;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.service.UserRegistrationValidator;
import org.kainos.ea.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "Authorization API")
@Path("/api")
public class AuthController {
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
    private AuthService authService = new AuthService(new UserDao(), new UserRegistrationValidator());

    @POST
    @Path("/auth/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserRegistrationRequest userRegistrationRequest) {
        try {
            return Response.ok(authService.registerUser(userRegistrationRequest)).build();
        } catch (FailedToRegisterUserException e) {
            logger.error("Failed to register user! Error: {}", e.getMessage());

            return Response.serverError().build();
        } catch (InvalidUserRegistrationRequestException e) {
            logger.error("Invalid user data! Error: {}", e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
}
