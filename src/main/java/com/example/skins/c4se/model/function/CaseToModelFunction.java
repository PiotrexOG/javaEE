package com.example.skins.c4se.model.function;


import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CaseModel;
import com.example.skins.skin.dto.GetSkinsResponse;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

public class CaseToModelFunction implements Function<Case, CaseModel> {
    @Override
    public CaseModel apply(Case gitRepository) {
        return CaseModel.builder()
                .id(gitRepository.getId())
                .name(gitRepository.getName())
                .skins(Optional.ofNullable(gitRepository.getSkins())
                        .orElse(new LinkedList<>())
                        .stream()
                        .map(commit -> GetSkinsResponse.Skin.builder()
                                .id(commit.getId())
                                .build())
                        .toList())
                .build();
    }
}
