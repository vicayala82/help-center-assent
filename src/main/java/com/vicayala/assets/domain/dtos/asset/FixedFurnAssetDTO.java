package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class FixedFurnAssetDTO extends FurnitureAssetDTO{
    @JsonIgnore
    public static final String TYPE = "fixed_furniture";
}
