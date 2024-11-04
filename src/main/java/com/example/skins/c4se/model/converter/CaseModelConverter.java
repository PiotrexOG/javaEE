package com.example.skins.c4se.model.converter;


import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CaseModel;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(forClass = CaseModel.class, managed = true)
public class CaseModelConverter implements Converter<CaseModel> {

    private final CaseService service;

    private final ModelFunctionFactory factory;

    @Inject
    public CaseModelConverter(CaseService service, ModelFunctionFactory factory){
        this.service = service;
        this.factory = factory;
    }

    @Override
    public CaseModel getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Case> Case = service.find(UUID.fromString(value));
        System.out.println(Case.get());
        System.out.println("siema");
        return Case.map(factory.CaseToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, CaseModel value) {
        return value == null ? "" : value.getId().toString();
    }
}
