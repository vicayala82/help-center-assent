package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vicayala.assets.domain.dtos.asset.AbstractAssetDTO;
import com.vicayala.assets.domain.dtos.asset.AssetDTO;
import com.vicayala.assets.domain.dtos.asset.ComputerTechAssetDTO;
import com.vicayala.assets.domain.dtos.asset.FixedFurnAssetDTO;
import com.vicayala.assets.domain.dtos.asset.LicenseAbstAssetDTO;
import com.vicayala.assets.domain.dtos.asset.MaintenanceFurnAssetDTO;
import com.vicayala.assets.domain.dtos.asset.ScreenTechAssetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractAssetVO.class, name = AbstractAssetVO.TYPE),
        @JsonSubTypes.Type(value = ComputerTechAssetVO.class, name = ComputerTechAssetVO.TYPE),
        @JsonSubTypes.Type(value = FixedFurnAssetVO.class, name = FixedFurnAssetVO.TYPE),
        @JsonSubTypes.Type(value = LicenseAbstAssetVO.class, name = LicenseAbstAssetVO.TYPE),
        @JsonSubTypes.Type(value = MaintenanceFurnAssetVO.class, name = MaintenanceFurnAssetVO.TYPE),
        @JsonSubTypes.Type(value = ScreenTechAssetVO.class, name = ScreenTechAssetVO.TYPE)
})
public abstract class AssetVO {

    private String id;
    @JsonProperty("responsible")
    private ResponsibleVO responsible;
    @JsonIgnore
    @JsonProperty("responsible_id")
    private String responsibleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;
    @JsonProperty("bill_number")
    private Long billNumber;
    @JsonProperty("description")
    private String description;

    public static AssetDTO toDTO(AssetVO assetVO){
        AssetDTO assetDTO;
        switch (assetVO) {
            case LicenseAbstAssetVO asset -> assetDTO = new LicenseAbstAssetDTO();
            case AbstractAssetVO asset-> assetDTO = new AbstractAssetDTO();
            case FixedFurnAssetVO asset -> assetDTO = new FixedFurnAssetDTO();
            case MaintenanceFurnAssetVO asset -> assetDTO = new MaintenanceFurnAssetDTO();
            case ComputerTechAssetVO asset -> assetDTO = new ComputerTechAssetDTO();
            case ScreenTechAssetVO asset -> assetDTO = new ScreenTechAssetDTO();
            default -> throw new RuntimeException();
        }
        BeanUtils.copyProperties(assetVO, assetDTO);
        if(Objects.nonNull(assetVO.getResponsible())){
            assetDTO.setResponsibleId(assetVO.getResponsibleId());
        }
        return assetDTO;
    }

}
