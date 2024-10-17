package com.example.skins.skin.dto.function;

import com.example.skins.skin.dto.GetCaseResponse;
import com.example.skins.skin.entity.Case;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts {@link Case} to {@link GetCaseResponse}.
 */
public class CaseToResponseFunction implements Function<Case, GetCaseResponse> {

    @Override
    public GetCaseResponse apply(Case entity) {
        return GetCaseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
