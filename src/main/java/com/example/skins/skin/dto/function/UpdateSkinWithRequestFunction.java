package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.entity.Skin;

import java.util.function.BiFunction;

public class UpdateSkinWithRequestFunction implements BiFunction<Skin, PatchSkinRequest, Skin> {
    @Override
    public Skin apply(Skin commit, PatchSkinRequest request) {
        return Skin.builder()
                .id(commit.getId())
                .caseItem(commit.getCaseItem())
                .name(commit.getName())
                .build();
    }
}
