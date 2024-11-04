package com.example.skins.c4se.model.function;


import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CasesModel;

import java.util.List;
import java.util.function.Function;

public class CasesToModelFunction implements Function<List<Case>, CasesModel> {
    @Override
    public CasesModel apply(List<Case> gitRepositories) {
        return CasesModel.builder()
                .cases(gitRepositories.stream()
                        .map(gitRepo -> CasesResponse.Case.builder()
                                .id(gitRepo.getId())
                                .name(gitRepo.getName())
                                .build())
                        .toList())
                .build();
    }
}
