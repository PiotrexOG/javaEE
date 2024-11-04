package com.example.skins.skin.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetSkinsResponse {

    @Data
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    public static class Skin{
        private UUID id;
        private String message;
        private String name;
    }

    private List<Skin> skins;
}
