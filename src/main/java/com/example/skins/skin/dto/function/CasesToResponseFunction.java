package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.GetCasesResponse;
import com.example.skins.skin.entity.Case;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List<Case>} to {@link GetCasesResponse}.
 */
public class CasesToResponseFunction implements Function<List<Case>, GetCasesResponse> {

    @Override
    public GetCasesResponse apply(List<Case> entities) {
        return GetCasesResponse.builder()
                .Cases(entities.stream()
                        .map(Case -> GetCasesResponse.Case.builder()
                                .id(Case.getId())
                                .name(Case.getName())
                                .build())
                        .toList())
                .build();
    }

}
