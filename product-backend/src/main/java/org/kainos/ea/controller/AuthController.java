package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.dao.UserDao;
import org.kainos.ea.exception.*;

import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.model.UserRegistrationRequest;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.service.UserRegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

@Tag(name = "Authorization API")
@Path("/api/auth")
public class AuthController {
    private final AuthService authService = new AuthService(new UserDao(), new AuthDao(), new UserRegistrationValidator());
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest login){
        try{
            return Response.ok(authService.login(login)).build();
        }catch (FailedToLoginException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }catch (FailedToGenerateTokenException | NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserRegistrationRequest userRegistrationRequest) {
        try {
            authService.registerUser(userRegistrationRequest);
            return Response.ok().build();
        } catch (FailedToRegisterUserException e) {
            logger.error("Failed to register user! Error: {}", e.getMessage());

            return Response.serverError().build();
        } catch (InvalidUserRegistrationRequestException e) {
            logger.error("Invalid user data! Error: {}", e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
}
