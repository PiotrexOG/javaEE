package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.entity.Skin;

import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Skin} based on provided value and updated with values from
 * {@link PatchSkinRequest}.
 */
public class UpdateSkinWithRequestFunction implements BiFunction<Skin, PatchSkinRequest, Skin> {

    @Override
    public Skin apply(Skin entity, PatchSkinRequest request) {
        return Skin.builder()
                .id(entity.getId())
                .name(request.getName())
                .floatValue(request.getFloatValue())
              //  .caseItem(request.getCase())
              //  .user(request.getUser())
                .build();
    }

}
