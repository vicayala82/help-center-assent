package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
@JsonTypeName(ScreenTechAssetVO.TYPE)
public class ScreenTechAssetVO extends TechnologyAssetVO{

    @JsonIgnore
    public static final String TYPE = "screen_tech";
    private double inches;

}
