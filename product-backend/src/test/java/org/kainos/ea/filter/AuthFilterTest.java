package org.kainos.ea.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.auth0.jwt.JWT.decode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthFilterTest {

    AuthFilter authFilter = new AuthFilter();
    String userToken = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidXNlckBrYWlub3MuY29tIiwicm9sZUlkIjoxLCJpYXQiOjE2OTY0MzMwNzEsImV4cCI6MTY5NjQzNjY3MX0.7Qn45K0Y7eW3AlkO_8n3Uc3hfBV1LCjvNs2xACdebME";

    @Test
    public void filter_ShouldDoNothing_WhenPathContainsAuth(){
        //given
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);
        UriInfo uriInfo = Mockito.mock(UriInfo.class);
        //when
        when(requestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getPath()).thenReturn("auth");
        //then
        assertDoesNotThrow(() -> authFilter.filter(requestContext));
    }
    @Test
    public void filter_ShouldThrowWebApplicationException_WhenFailsToGetTokenFromHeader(){
        //given
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);
        UriInfo uriInfo = Mockito.mock(UriInfo.class);
        //when
        when(requestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getPath()).thenReturn("test");
        when(requestContext.getHeaderString("Authorization")).thenReturn(null);

        //then
        assertThatExceptionOfType(WebApplicationException.class)
                .isThrownBy(() -> authFilter.filter(requestContext));
    }

    @Test
    public void filter_ShouldThrowWebApplicationException_WhenPathContainsAdminAndTokenDoesNotHaveAdminPrivileges(){
        //given
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);
        UriInfo uriInfo = Mockito.mock(UriInfo.class);
        //when
        when(requestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getPath()).thenReturn("admin");
        when(requestContext.getHeaderString("Authorization")).thenReturn(userToken);
        //then
        assertThatExceptionOfType(WebApplicationException.class)
                .isThrownBy(() -> authFilter.filter(requestContext));
    }


}