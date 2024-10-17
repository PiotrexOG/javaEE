package com.example.skins.skin.controller.simple;

import com.example.skins.skin.controller.api.SkinController;
import com.example.skins.skin.dto.*;
import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.service.CaseService;
import com.example.skins.component.DtoFunctionFactory;
import com.example.skins.controller.servlet.exception.BadRequestException;
import com.example.skins.controller.servlet.exception.NotFoundException;
import com.example.skins.skin.service.SkinService;

import java.io.InputStream;
import java.util.UUID;

/**
 * Simple framework agnostic implementation of controller.
 */
public class SkinSimpleController implements SkinController {

    /**
     * Skin service.
     */
    private final SkinService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service Skin service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    public SkinSimpleController(SkinService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetSkinsResponse getSkins() {
        return factory.SkinsToResponse().apply(service.findAll());
    }

    @Override
    public GetSkinsResponse getCaseSkins(UUID id) {
        return service.findAllByCase(id)
                .map(factory.SkinsToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetSkinsResponse getUserSkins(UUID id) {
        return service.findAllByUser(id)
                .map(factory.SkinsToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetSkinResponse getSkin(UUID uuid) {
        return service.find(uuid)
                .map(factory.SkinToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putSkin(UUID id, PutSkinRequest request) {
        try {
            service.create(factory.requestToSkin().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchSkin(UUID id, PatchSkinRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateSkin().apply(entity, request)),
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
                }
        );
    }

    @Override
    public byte[] getSkinPortrait(UUID id) {
        throw new NotFoundException();
    }

    @Override
    public void putSkinPortrait(UUID id, InputStream portrait) {
        service.find(id).ifPresentOrElse(
                entity -> service.updatePortrait(id, portrait),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
