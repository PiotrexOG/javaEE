package com.example.skins.skin.controller.simple;

import com.example.skins.skin.controller.api.CaseController;
import com.example.skins.skin.dto.GetCasesResponse;
import com.example.skins.skin.service.CaseService;
import com.example.skins.component.DtoFunctionFactory;

/**
 * Simple framework agnostic implementation of controller.
 */
public class CaseSimpleController implements CaseController {

    /**
     * Case service.
     */
    private final CaseService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;


    /**
     * @param service Case service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    public CaseSimpleController(CaseService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetCasesResponse getCases() {
        return factory.CasesToResponse().apply(service.findAll());
    }

}
