package com.example.skins.skin.controller.rest;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.transaction.TransactionalException;
import lombok.extern.java.Log;
import com.example.skins.skin.controller.api.SkinController;
import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.dto.PutSkinRequest;
import com.example.skins.skin.service.SkinService;
import com.example.skins.component.DtoFunctionFactory;
import com.example.skins.c4se.service.CaseService;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class SkinRestController implements SkinController {
    private SkinService service;
    private final DtoFunctionFactory factory;
    private CaseService caseService;

    @Inject
    public SkinRestController(SkinService service, CaseService caseService, DtoFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * @param service profession service
     */
    @EJB
    public void setSkinService(SkinService service) {
        this.service = service;
    }

    /**
     * @param service profession service
     */
    @EJB
    public void setCaseService(CaseService service) {
        this.caseService = service;
    }


    @Override
    public GetSkinsResponse getSkins() {
        return factory.skinsToResponse().apply(service.findAll());
    }

    @Override
    public GetSkinsResponse getCaseSkins(UUID uuid) {
        return service.findAllByCase(uuid).map(factory.skinsToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetSkinResponse getCaseSkin(UUID uuid, UUID skinId) {
        var skins = service.findAllByCase(uuid);
        if(skins.isPresent()){
            try {
                var skin = skins.get().stream().filter(com -> com.getId().equals(skinId)).toList().get(0);
                return factory.skinToResponse().apply(skin);
            }
            catch (Exception e){ throw new NotFoundException("Skin not found"); }
        }
        throw new NotFoundException("Case not found");
    }

    @Override
    public GetSkinsResponse getUserSkins(UUID uuid) {
        return service.findAllByUser(uuid).map(factory.skinsToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetSkinResponse getSkin(UUID uuid) {
        return service.find(uuid).map(factory.skinToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public void putSkin(UUID id, PutSkinRequest request) {
        try {
            service.find(id).ifPresent(skin -> {
                throw new BadRequestException("Skin with id: " + id + " already exists!");
            });
            service.create(factory.requestToSkin().apply(id, request));
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchSkin(UUID id, PatchSkinRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateSkinWithRequestFunction().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteSkin(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                });

    }

    @Override
    public void putSkin(UUID caseId, UUID skinId, PutSkinRequest request) {
        request.setCaseId(caseId);
        putSkin(caseId, request);
//        try {
//            service.find(skinId).ifPresent(skin -> {
//                throw new BadRequestException("Skin with id: " + skinId + " already exists!");
//            });
//            service.create(factory.requestToSkin().apply(skinId, request));
//        } catch (IllegalArgumentException ex) {
//            throw new BadRequestException(ex);
//        }
    }

    @Override
    public void patchSkin(UUID caseId, UUID skinId, PatchSkinRequest request) {
       patchSkin(skinId, request);
    }

    @Override
    public void deleteSkin(UUID caseId, UUID skinId) {
        deleteSkin(skinId);
    }
}
