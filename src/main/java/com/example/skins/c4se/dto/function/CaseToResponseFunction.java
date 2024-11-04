package com.example.skins.c4se.dto.function;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

import com.example.skins.c4se.dto.CaseResponse;
import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.user.dto.GetUsersResponse;

public class CaseToResponseFunction implements Function<Case, CaseResponse> {
    @Override
    public CaseResponse apply(Case aCase) {
        return CaseResponse.builder()
                .id(aCase.getId())
                .name(aCase.getName())
                .skins(Optional.ofNullable(aCase.getSkins())
                        .orElse(new LinkedList<>())
                        .stream()
                        .map(skin -> GetSkinsResponse.Skin.builder()
                                .id(skin.getId())
                                .build())
                        .toList())
                .build();
    }
}
