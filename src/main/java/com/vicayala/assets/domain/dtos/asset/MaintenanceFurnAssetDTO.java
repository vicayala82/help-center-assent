package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
public class MaintenanceFurnAssetDTO extends FurnitureAssetDTO{

    @JsonIgnore
    public static final String TYPE = "maintenance_furniture";
    @JsonProperty("maintenance_responsible")
    private String maintenanceResponsible;

}
