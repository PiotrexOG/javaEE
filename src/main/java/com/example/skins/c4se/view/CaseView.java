package com.example.skins.c4se.view;

import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CaseModel;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.service.SkinService;
import jakarta.ejb.EJB;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class CaseView implements Serializable {
    private CaseService service;
    private SkinService skinService;
    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private CaseModel case_item;

    @EJB
    public void setCaseService(CaseService service){
        this.service = service;
    }

    @EJB
    public void setSkinService(SkinService skinService){
        this.skinService = skinService;
    }

    @Inject
    public CaseView(ModelFunctionFactory modelFunctionFactory) {
        factory = modelFunctionFactory;
    }

    public void init() throws IOException {

        Optional<Case> repo = service.find(id);
        if (repo.isPresent()) {
            Case aCase = repo.get();
            aCase.setSkins(skinService.findAllForCallerPrincipal().stream().filter(skin -> skin.getCaseItem().getId().equals(aCase.getId())).toList());
            this.case_item = factory.CaseToModel().apply(repo.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Repository not found");
        }
    }


    public void deleteSkin(GetSkinsResponse.Skin skin) throws IOException {
        skinService.delete(skin.getId());
        init();
       // return "Case_view?faces-redirect=true&id=" + id;
    }

}
