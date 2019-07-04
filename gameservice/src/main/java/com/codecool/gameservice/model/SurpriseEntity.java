package com.codecool.gameservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurpriseEntity {

    private String type;
    private String src;
    private String surpriseType;
}
