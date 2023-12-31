package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
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
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComputerTechAssetDTO.class, name = ComputerTechAssetDTO.TYPE),
        @JsonSubTypes.Type(value = ScreenTechAssetDTO.class, name = ScreenTechAssetDTO.TYPE)
})
public abstract class TechnologyAssetDTO extends AssetDTO {

    private String brand;
    private String location;
    private String detail;
    @JsonProperty("warranty_expiration")
    private LocalDate warrantyExpiration;
    @JsonProperty("warranty_detail")
    private String warrantyDetail;
    private TechnologyStatusEnum status;

}
