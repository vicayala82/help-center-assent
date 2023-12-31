package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class ScreenTechAssetDTO extends TechnologyAssetDTO{

    @JsonIgnore
    public static final String TYPE = "screen_tech";
    private double inches;

}
