package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
@JsonTypeName(FixedFurnAssetVO.TYPE)
public class FixedFurnAssetVO extends FurnitureAssetVO {
    @JsonIgnore
    public static final String TYPE = "fixed_furniture";
}
