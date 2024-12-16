package com.example.skins.c4se.view;

import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CasesModel;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;
import com.example.skins.skin.service.SkinService;
import jakarta.ejb.EJB;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named
public class CaseList {
    private CaseService service;
    private SkinService skinService;
    private CasesModel Cases;

    private final ModelFunctionFactory factory;

     @EJB
    public void setCaseService(CaseService service){
        this.service = service;
    }

    @EJB
    public void setSkinService(SkinService skinService){
        this.skinService = skinService;
    }

    @Inject
    public CaseList(ModelFunctionFactory modelFunctionFactory) {
        factory = modelFunctionFactory;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
    }

    public CasesModel getCases() {
        if (null == Cases) {
            Cases = factory.CasesToModel().apply(service.findAll());
        }
        return Cases;
    }

    public String deleteAction(CasesResponse.Case repo) {

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
