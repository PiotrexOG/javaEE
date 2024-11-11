package com.example.skins.c4se.contoller.rest;

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
    private final CaseService service;
    private final DtoFunctionFactory factory;
    private final SkinService skinService;

    @Inject
    public CaseRestController(CaseService service, DtoFunctionFactory factory, SkinService skinService) {
        this.service = service;
        this.factory = factory;
        this.skinService = skinService;
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
        skinService.findAllByCase(id).ifPresent(skins ->
                skins.forEach(skin -> skinService.delete(skin.getId())));
//        skinService.findAll().stream().filter(skin ->
//                skin.getCasesitory().getId().equals(id)).forEach(skin -> skinService.delete(skin.getId()));
        service.find(id).ifPresentOrElse(service::delete, () -> {
            throw new NotFoundException();
        });
    }
}
