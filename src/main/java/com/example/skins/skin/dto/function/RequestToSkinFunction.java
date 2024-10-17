package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.PutSkinRequest;
import com.example.skins.skin.entity.Case;
import com.example.skins.skin.entity.Skin;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutSkinRequest} to {@link Skin}. Caution, some fields are not set as they should be updated
 * by business logic.
 */
public class RequestToSkinFunction implements BiFunction<UUID, PutSkinRequest, Skin> {

    @Override
    public Skin apply(UUID id, PutSkinRequest request) {
        return Skin.builder()
                .id(id)
                .name(request.getName())
                .floatValue(request.getFloatValue())
                .caseItem(Case.builder()
                        .id(request.getCase())
                        .build())
                .build();
    }

}
