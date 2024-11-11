package com.example.skins.c4se.model.function;


import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CaseModel;
import com.example.skins.skin.dto.GetSkinsResponse;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

public class CaseToModelFunction implements Function<Case, CaseModel> {
    @Override
    public CaseModel apply(Case aCase) {
        return CaseModel.builder()
                .id(aCase.getId())
                .name(aCase.getName())
                .skins(Optional.ofNullable(aCase.getSkins())
                        .orElse(new LinkedList<>())
                        .stream()
                        .map(skin -> GetSkinsResponse.Skin.builder()
                                .id(skin.getId())
                                .name(skin.getName())
                                .floatValue(skin.getFloatValue())
                                .build())
                        .toList())
                .build();
    }
}
