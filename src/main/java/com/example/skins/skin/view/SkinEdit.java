package com.example.skins.skin.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import com.example.skins.skin.model.SkinEditModel;
import com.example.skins.skin.service.SkinService;
import com.example.skins.component.ModelFunctionFactory;

import com.example.skins.skin.entity.Skin;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single Skin edit form.
 */
@ViewScoped
@Named
public class SkinEdit implements Serializable {

    /**
     * Service for managing Skins.
     */
    private final SkinService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Skin id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Skin exposed to the view.
     */
    @Getter
    private SkinEditModel Skin;


    /**
     * @param service service for managing Skins
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkinEdit(SkinService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Skin> Skin = service.find(id);
        if (Skin.isPresent()) {
            this.Skin = factory.SkinToEditModel().apply(Skin.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Skin not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        service.update(factory.updateSkin().apply(service.find(id).orElseThrow(), Skin));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}
