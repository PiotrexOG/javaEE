package com.example.skins.skin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchSkinRequest {
    private String Message;
    private LocalDateTime timestamp;
    private int filesChangedCount;
}
