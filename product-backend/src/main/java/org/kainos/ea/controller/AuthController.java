package org.kainos.ea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.LoginRequest;
import org.kainos.ea.service.AuthService;

import java.security.NoSuchAlgorithmException;

@Tag(name="Auth")
@Path("/api/auth")
public class AuthController {
    public AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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
}
