package com.vicayala.assets.infraestructure.api.vo.asset;

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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FixedFurnAssetVO.class, name = FixedFurnAssetVO.TYPE),
    @JsonSubTypes.Type(value = MaintenanceFurnAssetVO.class, name = MaintenanceFurnAssetVO.TYPE)
})
public abstract class FurnitureAssetVO extends AssetVO {

    private String manufacturer;
    private String location;
    private Integer quantity;
    private String detail;
    private FurnitureStatusEnum status;

}
