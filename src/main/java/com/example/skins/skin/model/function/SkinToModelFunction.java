package com.example.skins.skin.model.function;

import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.model.SkinModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link Skin} to {@link SkinModel}.
 */
public class SkinToModelFunction implements Function<Skin, SkinModel>, Serializable {

    @Override
    public SkinModel apply(Skin entity) {
        return SkinModel.builder()
                .name(entity.getName())
                .age(entity.getAge())
                .Case(entity.getCaseItem().getName())
                .build();
    }

}
