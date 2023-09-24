package com.vicayala.assets.domain.dtos.asset;

import com.vicayala.assets.application.shared.enums.FurnitureStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class FurnitureAssetDTO extends AssetDTO{

    private String manufacturer;
    private String location;
    private Integer quantity;
    private String detail;
    private FurnitureStatusEnum status;

}
