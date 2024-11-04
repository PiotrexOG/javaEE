package com.example.skins.c4se.dto.function;

import com.example.skins.c4se.dto.PatchCaseRequest;
import com.example.skins.c4se.entity.Case;
import java.util.function.BiFunction;

public class UpdateCaseWithRequestFunction implements BiFunction<Case, PatchCaseRequest, Case> {
    @Override
    public Case apply(Case gitRepository, PatchCaseRequest patchCaseRequest) {
        return Case.builder()
                .id(gitRepository.getId())
                .name(patchCaseRequest.getName())
                .build();
    }
}
