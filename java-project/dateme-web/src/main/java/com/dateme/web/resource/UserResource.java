package com.dateme.web.resource;

import com.codahale.metrics.annotation.Timed;
import com.dateme.api.DateMeApi;
import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;
import com.dateme.core.model.User;
import com.dateme.web.json.ProfileJson;
import com.dateme.web.json.UserCreationJson;
import com.dateme.web.json.UserJson;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private DateMeApi api;

    public UserResource(DateMeApi api) {
        this.api = api;
    }

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = api.getAllUsers();
        Stream<UserJson> jsons = users.parallelStream().map(u -> new UserJson(u, api.getProfile(u)));
        return Response.ok(jsons.toArray()).build();
    }

    @GET
    @Path("/{email}")
    @RolesAllowed("USER")
    @Timed
    public Response getUser(@PathParam("email") String email) {
        Optional<User> result = api.findUserAccountByEmail(email);
        if (result.isPresent()) {
            User user = result.get();
            return Response.ok(new UserJson(user, api.getProfile(user))).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Timed
    public Response createUser(UserCreationJson json) {
        User user = api.createUser(json.getEmail(), json.getPw());
        Profile profile = api.createProfile(user, json.getName(), new RGB(json.getRgb()), json.getNumber());
        return Response.ok(new UserJson(user, Optional.of(profile))).build();
    }

    @PUT
    @Path("/{email}")
    @Timed
    @RolesAllowed("USER")
    public Response updateProfile(@PathParam("email") String email, ProfileJson json, @Auth User user) {
        if (user.email.equals(email)) {
            Optional<Profile> profile = api.findUserAccountByEmail(email).flatMap(u -> api.getProfile(u));

            if (profile.isPresent()) {
                api.updateProfile(email, json.getName(), new RGB(json.getColor()), json.getNumber());
                return getUser(email);
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }


}
