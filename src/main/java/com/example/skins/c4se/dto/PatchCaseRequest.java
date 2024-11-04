package com.example.skins.c4se.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchCaseRequest {
    private String name;
}
