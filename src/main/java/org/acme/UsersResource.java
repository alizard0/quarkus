package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.reactive.NoCache;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/api/users")
public class UsersResource {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    ObjectMapper mapper;

    @GET
    @Path("/me")
    @RolesAllowed("user")
    @NoCache
    @Produces(value = "application/json")
    public String me() throws JsonProcessingException {
        return mapper.writeValueAsString(new User(securityIdentity));
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    @NoCache
    @Produces(value = "application/json")
    public String admin() throws JsonProcessingException {
        return mapper.writeValueAsString(new User(securityIdentity));
    }

    @GET
    @Path("/settings")
    @RolesAllowed("superuser")
    @NoCache
    @Produces(value = "application/json")
    public String superuser() throws JsonProcessingException {
        return mapper.writeValueAsString(new User(securityIdentity));
    }

    public static class User {

        private final String userName;

        User(SecurityIdentity securityIdentity) {
            this.userName = securityIdentity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }
}