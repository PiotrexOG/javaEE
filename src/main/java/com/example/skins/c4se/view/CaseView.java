package com.example.skins.c4se.view;

import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CaseModel;
import com.example.skins.c4se.repository.api.CaseRepository;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.service.SkinService;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class CaseView implements Serializable {
    private final CaseService service;
    private final SkinService skinService;
    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private CaseModel case_item;

    @Inject
    public CaseView(CaseService service, SkinService skinService, ModelFunctionFactory modelFunctionFactory) {
        this.service = service;
        this.skinService =skinService;
        factory = modelFunctionFactory;
    }

    public void init() throws IOException {
        System.out.println("chuj");
        System.out.println(skinService.findAll());
        List<Case> siema = service.findAll();
        System.out.println(siema);
        Optional<Case> repo = service.find(id);
        if (repo.isPresent()) {

            System.out.println(id);
            System.out.println(repo.get().getSkins());
            this.case_item = factory.CaseToModel().apply(repo.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Repository not found");
        }
    }


    public String deleteCommit(GetSkinsResponse.Skin commit) throws IOException {
        skinService.delete(commit.getId());

        return "gitRepo_view?faces-redirect=true&id=" + id;
    }

}
