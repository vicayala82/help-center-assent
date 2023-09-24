package com.vicayala.assets.domain.dtos.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ComputerTechAssetDTO extends TechnologyAssetDTO{
    private String processor;
    private String ram;
    private String hardDrive;
}
