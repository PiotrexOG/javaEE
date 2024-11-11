package com.example.skins.skin.dto;

import com.example.skins.c4se.dto.CasesResponse;
import com.example.skins.c4se.entity.Case;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetSkinResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Case {
        private UUID uuid;

        private String name;
    }


    private UUID id;

    private Float floatValue;

    private String name;
    private Case Case;
}
