package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.entity.Skin;

import java.util.function.Function;

/**
 * Converts {@link Skin} to {@link GetSkinResponse}.
 */
public class SkinToResponseFunction implements Function<Skin, GetSkinResponse> {

    @Override
    public GetSkinResponse apply(Skin entity) {
        return GetSkinResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .floatValue(entity.getFloatValue())
                .Case(GetSkinResponse.Case.builder()
                        .id(entity.getCaseItem().getId())
                        .name(entity.getCaseItem().getName())
                        .build())
                .build();
    }

}
