package com.example.skins.skin.model.function;

import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.model.SkinsModel;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List<Skin>} to {@link SkinsModel}.
 */
public class SkinsToModelFunction implements Function<List<Skin>, SkinsModel> {

    @Override
    public SkinsModel apply(List<Skin> entity) {
        return SkinsModel.builder()
                .Skins(entity.stream()
                        .map(Skin -> SkinsModel.Skin.builder()
                                .id(Skin.getId())
                                .name(Skin.getName())
                                .build())
                        .toList())
                .build();
    }

}
