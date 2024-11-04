package com.example.skins.c4se.dto;

import com.example.skins.skin.dto.GetSkinsResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CaseResponse {
    private UUID id;
    private String name;
    private LocalDate creationDate;
    private List<GetSkinsResponse.Skin> skins;
}
