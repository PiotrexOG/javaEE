package com.example.skins.skin.dto;

import lombok.*;

import java.time.LocalDateTime;
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
        private Float floatValue;
        private String name;
        private Long version;
        private LocalDateTime creationDateTime;
        private LocalDateTime editDateTime;
    }

    private List<Skin> skins;


}
