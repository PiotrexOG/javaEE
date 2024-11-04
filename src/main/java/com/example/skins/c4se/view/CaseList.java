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
    private CasesModel gitRepos;

    private final ModelFunctionFactory factory;

    @Inject
    public CaseList(CaseService service, SkinService skinService, ModelFunctionFactory modelFunctionFactory) {
        this.service = service;
        this.skinService =skinService;
        factory = modelFunctionFactory;
    }

    public CasesModel getCases() {
        if (null == gitRepos) {
            gitRepos = factory.CasesToModel().apply(service.findAll());
        }
        return gitRepos;
    }

    public String deleteAction(CasesResponse.Case repo) {
        skinService.findAllByCase(repo.getId()).ifPresent(commits ->
                commits.forEach(commit -> skinService.delete(commit.getId())));
        Optional<Case> foundRepo = service.find(repo.getId());
        foundRepo.ifPresent(service::delete);
        return "gitRepo_list?faces-redirect=true";
    }


}
