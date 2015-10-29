package com.dateme.web.resource;

import com.codahale.metrics.annotation.Timed;
import com.dateme.api.DateMeApi;
import com.dateme.core.model.Profile;
import com.dateme.web.json.ProfileJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private DateMeApi api;

    public ProfileResource(DateMeApi api) {
        this.api = api;
    }


    @GET
    @Path("/profile/{email}")
    @Timed
    public Response getProfile(@PathParam("email") String email) {
        Optional<Profile> result = api.findUserAccountByEmail(email);
        if (result.isPresent()) {
            return Response.ok(new ProfileJson(result.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
