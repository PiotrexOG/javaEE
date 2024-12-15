package com.example.skins.skin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchSkinRequest {
    private String name;
    private float floatValue;
    private Long version;
}
