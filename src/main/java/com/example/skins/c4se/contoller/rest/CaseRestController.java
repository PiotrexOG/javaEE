package com.example.skins.c4se.contoller.rest;

import jakarta.ejb.EJB;
import com.example.skins.skin.service.SkinService;
import com.example.skins.component.DtoFunctionFactory;
import com.example.skins.c4se.contoller.api.CaseController;
import com.example.skins.c4se.dto.CaseResponse;
import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.dto.PatchCaseRequest;
import com.example.skins.c4se.dto.PutCaseRequest;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.service.CaseService;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;

import java.util.Optional;
import java.util.UUID;

@Path("")
public class CaseRestController implements CaseController {
    private CaseService service;
    private final DtoFunctionFactory factory;
    private SkinService skinService;

    @Inject
    public CaseRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * @param service profession service
     */
    @EJB
    public void setCaseService(CaseService service) {
        this.service = service;
    }

    /**
     * @param service profession service
     */
    @EJB
    public void setSkinService(SkinService service) {
        this.skinService = service;
    }

    @Override
    public CasesResponse getCases() {
        return factory.casesToResponse().apply(service.findAll());
    }

    @Override
    public CaseResponse getCase(UUID id) {
        return service.find(id).map(factory.caseToResponse()).orElseThrow(NotFoundException::new);

    }

    @Override
    public void putCase(UUID id, PutCaseRequest request) {
        Optional<Case> acase = service.find(id);
        if (acase.isPresent()) {
            throw new BadRequestException("Case with id " + id + " already exists");
        }
        try {
            service.create(factory.requestToCase().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }
    }

    @Override
    public void patchCase(UUID id, PatchCaseRequest request) {
        service.find(id).ifPresentOrElse(acase -> service.update(factory.updateCaseWithRequest().apply(acase, request)), () -> {
            throw new NotFoundException();
        });
    }

       @Override
    public void delete(UUID id) {
           service.find(id).ifPresentOrElse(
                   entity -> service.delete(id),
                   () -> {
                       throw new NotFoundException("Repo not found");
                   }
           );
       }
}
