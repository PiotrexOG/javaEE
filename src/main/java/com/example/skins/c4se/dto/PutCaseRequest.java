package com.example.skins.c4se.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutCaseRequest {
    private String name;
    private LocalDate creationDate;
    private UUID ownerId;
}
