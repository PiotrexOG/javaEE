package com.example.skins.skin.controller.api;

import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.dto.PutSkinRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface SkinController {
    @GET
    @Path("/skins")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkinsResponse getSkins();

    @GET
    @Path("/cases/{id}/skins")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkinsResponse getCaseSkins(@PathParam("id") UUID uuid);

    @GET
    @Path("/cases/{caseId}/skins/{skinId}")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkinResponse getCaseSkin(@PathParam("caseId") UUID uuid, @PathParam("skinId") UUID skinId);

    @GET
    @Path("/user/{id}/skins")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkinsResponse getUserSkins(@PathParam("id") UUID uuid);

    @GET
    @Path("/skins/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkinResponse getSkin(@PathParam("id") UUID uuid);

    @PUT
    @Path("/skins/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putSkin(@PathParam("id") UUID id, PutSkinRequest request);

    @PATCH
    @Path("/skins/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchSkin(@PathParam("id") UUID id, PatchSkinRequest request);

    @DELETE
    @Path("/skins/{id}")
    void deleteSkin(@PathParam("id") UUID id);

    @PUT
    @Path("/cases/{caseId}/skins/{skinId}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putSkin(@PathParam("caseId") UUID caseId, @PathParam("skinId") UUID skinId, PutSkinRequest request);

    @PATCH
    @Path("/cases/{caseId}/skins/{skinId}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchSkin(@PathParam("caseId") UUID caseId, @PathParam("skinId") UUID skinId, PatchSkinRequest request);

    @DELETE
    @Path("/cases/{caseId}/skins/{skinId}")
    void deleteSkin(@PathParam("caseId") UUID caseId, @PathParam("skinId") UUID skinId);
}
