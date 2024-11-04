package com.example.skins.c4se.model;

import lombok.*;

import java.util.List;
import java.util.UUID;
import com.example.skins.skin.dto.GetSkinsResponse;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CaseModel {
    private UUID id;
    private String name;
    private String creationDate;
    private String visibility;
    private String owner;
    private List<GetSkinsResponse.Skin> skins;
}
