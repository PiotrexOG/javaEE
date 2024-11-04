package com.example.skins.c4se.model;

import com.example.skins.c4se.dto.CasesResponse;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CasesModel {
    @Singular
    public List<CasesResponse.Case> cases;
}
