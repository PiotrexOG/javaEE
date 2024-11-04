package com.example.skins.skin.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.example.skins.skin.model.SkinsModel;
import com.example.skins.skin.service.SkinService;
import com.example.skins.component.ModelFunctionFactory;

/**
 * View bean for rendering list of Skins.
 */
@RequestScoped
@Named
public class SkinList {

    /**
     * Service for managing Skins.
     */
    private final SkinService service;

    /**
     * Skins list exposed to the view.
     */
    private SkinsModel Skins;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * @param service Skin service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkinList(SkinService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all Skins
     */
    public SkinsModel getSkins() {
        if (Skins == null) {
            Skins = factory.SkinsToModel().apply(service.findAll());
        }
        return Skins;
    }

    /**
     * Action for clicking delete action.
     *
     * @param Skin Skin to be removed
     * @return navigation case to list_Skins
     */
    public String deleteAction(SkinsModel.Skin Skin) {
        service.delete(Skin.getId());
        return "skin_list?faces-redirect=true";
    }

}
