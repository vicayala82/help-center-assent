package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vicayala.assets.application.shared.enums.FurnitureStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(value = FixedFurnAssetDTO.class, name = FixedFurnAssetDTO.TYPE),
    @JsonSubTypes.Type(value = MaintenanceFurnAssetDTO.class, name = MaintenanceFurnAssetDTO.TYPE)
})
public abstract class FurnitureAssetDTO extends AssetDTO{

    private String manufacturer;
    private String location;
    private Integer quantity;
    private String detail;
    private FurnitureStatusEnum status;

}
