package com.example.skins.c4se.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CasesResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Case{
        private UUID id;
        private String name;
    }

    private List<Case> cases;
}
