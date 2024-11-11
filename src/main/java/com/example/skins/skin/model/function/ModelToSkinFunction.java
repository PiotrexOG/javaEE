package com.example.skins.skin.model.function;

import com.example.skins.skin.entity.Skin;
import lombok.SneakyThrows;
import com.example.skins.c4se.entity.Case;
import com.example.skins.skin.model.SkinCreateModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link SkinCreateModel} to {@link Skin}.
 */
public class ModelToSkinFunction implements Function<SkinCreateModel, Skin>, Serializable {

    @Override
    @SneakyThrows
    public Skin apply(SkinCreateModel model) {
        return Skin.builder()
                .id(model.getId())
                .name(model.getName())
                .floatValue(model.getFloatValue())
                .caseItem(Case.builder()
                        .id(model.getAcase().getId())
                        .build())
                .build();
    }

}
