package com.example.skins.user.controller.api;

import com.example.skins.user.dto.GetUserResponse;
import com.example.skins.user.dto.GetUsersResponse;
import com.example.skins.user.dto.PatchUserRequest;
import com.example.skins.user.dto.PutUserRequest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.InputStream;
import java.util.UUID;

/**
 * Controller for managing collections Cases' representations.
 */
@Path("")
public interface UserController {
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUserResponse getUser(@PathParam("id") UUID id);

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    GetUsersResponse getUsers();

    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putUser(@PathParam("id") UUID id, PutUserRequest request);

    @PATCH
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchUser(@PathParam("id") UUID id, PatchUserRequest request);

    @DELETE
    @Path("/users/{id}")
    void deleteUser(@PathParam("id") UUID id);

    @GET
    @Path("/users/{id}/avatar")
    @Produces("image/png")
    byte[] getUserAvatar(@PathParam("id") UUID id);

//    @PUT
//    @Path("/users/{id}/avatar")a
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    void putUserAvatar(@PathParam("id") UUID id, @FormParam("avatar") InputStream avatar);

    @PATCH
    @Path("/users/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    void patchUserAvatar(@PathParam("id") UUID id, @FormParam("avatar") InputStream avatar);

    @DELETE
    @Path("/users/{id}/avatar")
    void deleteUserAvatar(@PathParam("id") UUID id);
}
