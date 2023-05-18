package com.example.webprojekatjun.resources;

import com.example.webprojekatjun.entities.User;
import com.example.webprojekatjun.requests.LoginRequest;
import com.example.webprojekatjun.requests.RegisterRequest;
import com.example.webprojekatjun.requests.SetStatusRequest;
import com.example.webprojekatjun.requests.UpdateUserRequest;
import com.example.webprojekatjun.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    public Response register(@Valid RegisterRequest registerRequest)
    {
        Map<String, Object> response = new HashMap<>();

        User user = this.userService.register(registerRequest.getUsername(), registerRequest.getPassword(),registerRequest.getIme(),registerRequest.getPrezime(),registerRequest.getTip(),true);
        if (user == null) {
            response.put("message", "To korisnicko ime vec postoji!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("user", user);

        return Response.ok(response).build();
    }

    @POST
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateUser(@Valid UpdateUserRequest updateUserRequest)
    {
        Map<String, Object> response = new HashMap<>();

        boolean updated = this.userService.updateUser(updateUserRequest.getEmail(), updateUserRequest.getNewEmail(),updateUserRequest.getIme(),updateUserRequest.getPrezime(),updateUserRequest.getTip());
        if (!updated) {
            response.put("message", "To korisnicko ne postoji!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("message", "Uspesno azuriran korisnik");

        return Response.ok(response).build();
    }

    @POST
    @Path("/status")
    @Produces({MediaType.APPLICATION_JSON})
    public Response setStatus(@Valid SetStatusRequest setStatusRequest)
    {
        Map<String, Object> response = new HashMap<>();

        boolean set = this.userService.setStatus(setStatusRequest.getUsername(), setStatusRequest.isAktivan());
        if (!set) {
            response.put("message", "To korisnicko ime ne postoji!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("message","Uspesno promenjeno");

        return Response.ok(response).build();
    }
}
