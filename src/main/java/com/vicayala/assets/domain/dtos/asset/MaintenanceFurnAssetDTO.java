package com.vicayala.assets.domain.dtos.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceFurnAssetDTO extends FurnitureAssetDTO{

    private String maintenanceResponsible;

}
