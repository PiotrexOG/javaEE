package com.example.skins.skin.model.function;

import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.model.SkinEditModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link Skin} to {@link SkinEditModel}.
 */
public class SkinToEditModelFunction implements Function<Skin, SkinEditModel>, Serializable {

    @Override
    public SkinEditModel apply(Skin entity) {
        return SkinEditModel.builder()
                .name(entity.getName())
                .age(entity.getAge())
                .background(entity.getBackground())
                .build();
    }

}
