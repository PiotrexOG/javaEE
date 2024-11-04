package com.example.skins.skin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutSkinRequest {
    private LocalDateTime timestamp;
    private String message;
    private int filesChangedCount;
    private UUID authorId;
    private UUID repoId;
}
