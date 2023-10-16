package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vicayala.assets.application.shared.enums.TechnologyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComputerTechAssetVO.class, name = ComputerTechAssetVO.TYPE),
        @JsonSubTypes.Type(value = ScreenTechAssetVO.class, name = ScreenTechAssetVO.TYPE)
})
public abstract class TechnologyAssetVO extends AssetVO {

    private String brand;
    private String location;
    private String detail;
    @JsonProperty("warranty_expiration")
    private LocalDate warrantyExpiration;
    @JsonProperty("warranty_detail")
    private String warrantyDetail;
    private TechnologyStatusEnum status;

}
