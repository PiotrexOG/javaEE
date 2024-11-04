package com.example.skins.c4se.dto.function;


import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;

import java.util.List;
import java.util.function.Function;

public class CasesToResponseFunction implements Function<List<Case>, CasesResponse> {

    @Override
    public CasesResponse apply(List<Case> Cases) {
        return CasesResponse.builder()
                .cases(Cases.stream()
                        .map(Case -> CasesResponse.Case.builder()
                                .id(Case.getId())
                                .name(Case.getName())
                                .build())
                        .toList())
                .build();
    }
}
