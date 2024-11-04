package com.example.skins.skin.dto.function;


import com.example.skins.c4se.entity.Case;
import com.example.skins.skin.dto.PutSkinRequest;
import com.example.skins.skin.entity.Skin;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToSkinFunction implements BiFunction<UUID, PutSkinRequest, Skin> {
    @Override
    public Skin apply(UUID uuid, PutSkinRequest request) {
        return Skin.builder()
                .id(uuid)
                .caseItem(Case.builder()
                        .id(request.getRepoId())
                        .build())
                .build();
    }
}
