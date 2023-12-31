package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class ComputerTechAssetDTO extends TechnologyAssetDTO{
    @JsonIgnore
    public static final String TYPE = "computer_tech";
    private String processor;
    private String ram;
    private String hardDrive;
}
