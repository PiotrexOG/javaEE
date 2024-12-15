package com.example.skins.skin.view;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
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
    private SkinService service;

    private final FacesContext facesContext;

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

    @Setter
    @Getter
    private UUID initialCase;

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
    public SkinEdit(ModelFunctionFactory factory, FacesContext facesContext) {
        this.factory = factory;
        this.facesContext = facesContext;
    }

    @EJB
    public void setService(SkinService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Skin> Skin = service.find(id);
        if (Skin.isPresent()) {
            this.Skin = factory.SkinToEditModel().apply(Skin.get());
            this.initialCase = this.Skin.getACase().getId();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Skin not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() throws IOException {

        try {
            service.update(factory.updateSkin().apply(service.find(id).orElseThrow(), Skin), initialCase);
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                init();
                facesContext.addMessage(null, new FacesMessage("Version collision."));
            }
            return null;
        }

    }

}
