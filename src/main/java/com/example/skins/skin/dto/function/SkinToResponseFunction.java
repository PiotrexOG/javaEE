package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.entity.Skin;

import java.util.function.Function;

public class SkinToResponseFunction implements Function<Skin, GetSkinResponse> {
    @Override
    public GetSkinResponse apply(Skin commit) {
        return GetSkinResponse.builder()
                .id(commit.getId())
                .Case(GetSkinResponse.Case.builder()
                        .name(commit.getCaseItem().getName())
                        .uuid(commit.getCaseItem().getId())
                        .build())
                .build();
    }
}
