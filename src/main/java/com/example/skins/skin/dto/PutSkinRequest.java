package com.example.skins.skin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutSkinRequest {
    private String name;
    private float floatValue;
    private UUID userId;
    private UUID caseId;
    private Long version;
}
