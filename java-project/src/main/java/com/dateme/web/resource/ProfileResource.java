package com.dateme.web.resource;

import com.codahale.metrics.annotation.Timed;
import com.dateme.api.DateMeApi;
import com.dateme.core.model.Profile;
import com.dateme.web.json.ProfileJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/profile")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private DateMeApi api;

    public ProfileResource(DateMeApi api) {
        this.api = api;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfiles() {
        List<Profile> profiles = api.getAllProfiles();
        return Response.ok(profiles.parallelStream().map(ProfileJson::new).toArray()).build();
    }



    @GET
    @Path("/{email}")
    @Timed
    public Response getProfile(@PathParam("email") String email) {
        Optional<Profile> result = api.findUserAccountByEmail(email);
        if (result.isPresent()) {
            return Response.ok(new ProfileJson(result.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Timed
    public Response createProfile(ProfileJson profileJson) {
        Profile profile = profileJson.toProfile();
        return Response.ok(new ProfileJson(api.createUserAccount(profile))).build();
    }


}
