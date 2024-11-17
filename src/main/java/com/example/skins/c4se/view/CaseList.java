package com.example.skins.c4se.view;

import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CasesModel;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;
import com.example.skins.skin.service.SkinService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named
public class CaseList {
    private final CaseService service;
    private final SkinService skinService;
    private CasesModel Cases;

    private final ModelFunctionFactory factory;

    @Inject
    public CaseList(CaseService service, SkinService skinService, ModelFunctionFactory modelFunctionFactory) {
        this.service = service;
        this.skinService =skinService;
        factory = modelFunctionFactory;
    }

    public CasesModel getCases() {
        if (null == Cases) {
            Cases = factory.CasesToModel().apply(service.findAll());
        }
        return Cases;
    }

    public String deleteAction(CasesResponse.Case repo) {
        System.out.println(skinService.findAllByCase(repo.getId()));

        //todo

        skinService.findAllByCase(repo.getId()).ifPresent(skins ->
                skins.forEach(skin -> System.out.println(skin.getName())));

        skinService.findAllByCase(repo.getId()).ifPresent(skins ->
                skins.forEach(skin -> skinService.delete(skin.getId())));
        System.out.println(skinService.findAll());
        Optional<Case> foundRepo = service.find(repo.getId());
        //foundRepo.ifPresent(service::delete);
        foundRepo.ifPresent(repository -> service.delete(repository.getId()));
        return "Case_list?faces-redirect=true";
    }


}
