package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@JsonTypeName(MaintenanceFurnAssetVO.TYPE)
public class MaintenanceFurnAssetVO extends FurnitureAssetVO {

    @JsonIgnore
    public static final String TYPE = "maintenance_furniture";
    @JsonProperty("maintenance_responsible")
    private String maintenanceResponsible;

}
