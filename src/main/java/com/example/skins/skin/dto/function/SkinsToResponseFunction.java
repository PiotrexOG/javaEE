package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.entity.Skin;

import java.util.List;
import java.util.function.Function;

/**
 * Coverts {@link List<Skin>} to {@link GetSkinsResponse}.
 */
public class SkinsToResponseFunction implements Function<List<Skin>, GetSkinsResponse> {

    @Override
    public GetSkinsResponse apply(List<Skin> entities) {
        return GetSkinsResponse.builder()
                .Skins(entities.stream()
                        .map(Skin -> GetSkinsResponse.Skin.builder()
                                .id(Skin.getId())
                                .name(Skin.getName())
                                .build())
                        .toList())
                .build();
    }

}
