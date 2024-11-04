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
    public CaseResponse apply(Case gitRepo) {
        return CaseResponse.builder()
                .id(gitRepo.getId())
                .name(gitRepo.getName())
                .skins(Optional.ofNullable(gitRepo.getSkins())
                        .orElse(new LinkedList<>())
                        .stream()
                        .map(commit -> GetSkinsResponse.Skin.builder()
                                .id(commit.getId())
                                .build())
                        .toList())
                .build();
    }
}
