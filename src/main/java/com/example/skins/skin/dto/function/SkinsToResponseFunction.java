package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.entity.Skin;

import java.util.List;
import java.util.function.Function;

public class SkinsToResponseFunction implements Function<List<Skin>, GetSkinsResponse> {

    @Override
    public GetSkinsResponse apply(List<Skin> commits) {
        return GetSkinsResponse.builder()
                .skins(commits.stream()
                        .map(commit -> GetSkinsResponse.Skin.builder()
                                .id(commit.getId())
                                .build())
                        .toList())
                .build();
    }
}
