package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.entity.Skin;

import java.util.List;
import java.util.function.Function;

public class SkinsToResponseFunction implements Function<List<Skin>, GetSkinsResponse> {

    @Override
    public GetSkinsResponse apply(List<Skin> skins) {
        return GetSkinsResponse.builder()
                .skins(skins.stream()
                        .map(skin -> GetSkinsResponse.Skin.builder()
                                .id(skin.getId())
                                .message("test")
                                .build())
                        .toList())
                .build();
    }
}
