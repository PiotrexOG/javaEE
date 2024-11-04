package com.example.skins.c4se.dto.function;

import com.example.skins.c4se.dto.PutCaseRequest;
import com.example.skins.c4se.entity.Case;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToCase implements BiFunction<UUID, PutCaseRequest, Case> {
    @Override
    public Case apply(UUID uuid, PutCaseRequest putCaseRequest) {
        return Case.builder()
                .id(uuid)
                .name(putCaseRequest.getName())
                .build();
    }
}
