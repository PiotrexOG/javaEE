package com.example.skins.c4se.model.function;


import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.model.CasesModel;

import java.util.List;
import java.util.function.Function;

public class CasesToModelFunction implements Function<List<Case>, CasesModel> {
    @Override
    public CasesModel apply(List<Case> Cases) {
        return CasesModel.builder()
                .cases(Cases.stream()
                        .map(Case -> CasesResponse.Case.builder()
                                .id(Case.getId())
                                .name(Case.getName())
                                .build())
                        .toList())
                .build();
    }
}
