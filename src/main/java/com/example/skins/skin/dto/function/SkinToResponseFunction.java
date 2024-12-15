package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.entity.Skin;

import java.util.function.Function;

public class SkinToResponseFunction implements Function<Skin, GetSkinResponse> {
    @Override
    public GetSkinResponse apply(Skin skin) {
        return GetSkinResponse.builder()
                .id(skin.getId())
                .name(skin.getName())
                .floatValue(skin.getFloatValue())
                .version(skin.getVersion())
                .Case(GetSkinResponse.Case.builder()
                        .name(skin.getCaseItem().getName())
                        .uuid(skin.getCaseItem().getId())
                        .build())
                .build();
    }
}
