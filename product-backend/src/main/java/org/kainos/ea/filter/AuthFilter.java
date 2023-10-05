package org.kainos.ea.filter;


import com.auth0.jwt.JWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;

import java.util.Date;


public class AuthFilter implements ContainerRequestFilter {
    private final String secretKey = System.getenv("JWT_SECRET");

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        if (!path.contains("auth")) {
                String token = requestContext.getHeaderString("Authorization");
                if (token == null) {
                    throw new WebApplicationException("User unauthorized", Response.Status.UNAUTHORIZED);
                }
                if(!verify(token,secretKey)){
                    throw new WebApplicationException("Invalid token", Response.Status.UNAUTHORIZED);
                }
                try {
                    DecodedJWT jwtData = JWT.decode(token);
                    if (path.contains("admin")) {
                        if (jwtData.getClaim("roleId").asInt() != 2) {
                            throw new WebApplicationException("No admin privileges", Response.Status.UNAUTHORIZED);
                        }
                    }
                }catch (Exception e){
                    throw new WebApplicationException("User unauthorized", Response.Status.UNAUTHORIZED);
                }
        }
    }

    public static boolean verify(String token, String key) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            if (expiresAt != null && expiresAt.before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
