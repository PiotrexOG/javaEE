package com.example.skins.c4se.contoller.api;

import com.example.skins.c4se.dto.CaseResponse;
import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.dto.PatchCaseRequest;
import com.example.skins.c4se.dto.PutCaseRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface CaseController {

    @GET
    @Path("/cases")
    @Produces(MediaType.APPLICATION_JSON)
    CasesResponse getCases();

    @GET
    @Path("/cases/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CaseResponse getCase(@PathParam("id") UUID id);

    @PUT
    @Path("/cases/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putCase(@PathParam("id") UUID id, PutCaseRequest request);

    @PATCH
    @Path("/cases/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchCase(@PathParam("id") UUID id, PatchCaseRequest request);

    @DELETE
    @Path("/cases/{id}")
    void delete(@PathParam("id") UUID id);
}
