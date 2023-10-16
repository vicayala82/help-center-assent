package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@JsonTypeName(ComputerTechAssetVO.TYPE)
public class ComputerTechAssetVO extends TechnologyAssetVO {
    @JsonIgnore
    public static final String TYPE = "computer_tech";
    private String processor;
    private String ram;
    private String hardDrive;
}
