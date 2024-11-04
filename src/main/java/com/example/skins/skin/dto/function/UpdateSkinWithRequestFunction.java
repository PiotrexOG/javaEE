package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.entity.Skin;

import java.util.function.BiFunction;

public class UpdateSkinWithRequestFunction implements BiFunction<Skin, PatchSkinRequest, Skin> {
    @Override
    public Skin apply(Skin skin, PatchSkinRequest request) {
        return Skin.builder()
                .id(skin.getId())
                .caseItem(skin.getCaseItem())
                .name(skin.getName())
                .build();
    }
}
