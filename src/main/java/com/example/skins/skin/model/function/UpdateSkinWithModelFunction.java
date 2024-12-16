package com.example.skins.skin.model.function;

import lombok.SneakyThrows;
import com.example.skins.skin.model.SkinEditModel;
import com.example.skins.skin.entity.Skin;
import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Skin} based on provided value and updated with values from
 * {@link SkinEditModel}.
 */
public class UpdateSkinWithModelFunction implements BiFunction<Skin, SkinEditModel, Skin>, Serializable {

    @Override
    @SneakyThrows
    public Skin apply(Skin entity, SkinEditModel request) {
        return Skin.builder()
                .user(entity.getUser())
                .id(entity.getId())
                .name(request.getName())
                .floatValue(request.getFloatValue())
                .caseItem(entity.getCaseItem())
                .version(request.getVersion())
                .creationDateTime(entity.getCreationDateTime())
                .build();
    }

}
