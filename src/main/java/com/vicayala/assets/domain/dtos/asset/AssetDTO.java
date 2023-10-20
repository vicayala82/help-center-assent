package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vicayala.assets.infraestructure.api.handler.ResponsibleHandler;
import com.vicayala.assets.infraestructure.api.vo.asset.AbstractAssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.AssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.ComputerTechAssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.FixedFurnAssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.LicenseAbstAssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.MaintenanceFurnAssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.ScreenTechAssetVO;
import com.vicayala.assets.infraestructure.db.entities.asset.AssetsItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractAssetDTO.class, name = AbstractAssetDTO.TYPE),
        @JsonSubTypes.Type(value = ComputerTechAssetDTO.class, name = ComputerTechAssetDTO.TYPE),
        @JsonSubTypes.Type(value = FixedFurnAssetDTO.class, name = FixedFurnAssetDTO.TYPE),
        @JsonSubTypes.Type(value = LicenseAbstAssetDTO.class, name = LicenseAbstAssetDTO.TYPE),
        @JsonSubTypes.Type(value = MaintenanceFurnAssetDTO.class, name = MaintenanceFurnAssetDTO.TYPE),
        @JsonSubTypes.Type(value = ScreenTechAssetDTO.class, name = ScreenTechAssetDTO.TYPE)
})
public abstract class AssetDTO {

    private String id;
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

    public static AssetVO toVO(AssetDTO assetDTO){
        AssetVO assetVO;
        switch (assetDTO) {
            case LicenseAbstAssetDTO asset -> assetVO = new LicenseAbstAssetVO();
            case AbstractAssetDTO asset-> assetVO = new AbstractAssetVO();
            case FixedFurnAssetDTO asset -> assetVO = new FixedFurnAssetVO();
            case MaintenanceFurnAssetDTO asset -> assetVO = new MaintenanceFurnAssetVO();
            case ComputerTechAssetDTO asset -> assetVO = new ComputerTechAssetVO();
            case ScreenTechAssetDTO asset -> assetVO = new ScreenTechAssetVO();
            default -> throw new RuntimeException();
        }
        BeanUtils.copyProperties(assetDTO, assetVO);
        if(StringUtils.isNotEmpty(assetDTO.getResponsibleId())){
            assetVO.setResponsible(ResponsibleHandler
                .createResponsibleVO(assetDTO.getResponsibleId()));
        }
        return assetVO;
    }

    public static AssetsItemEntity ConvertToItemEntity(AssetDTO assetDTO){
        AssetsItemEntity assetsItemEntity = AssetsItemEntity.builder().build();
        switch (assetDTO) {
            case LicenseAbstAssetDTO asset -> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(LicenseAbstAssetDTO.TYPE);
            }
            case AbstractAssetDTO asset-> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(AbstractAssetDTO.TYPE);
            }
            case FixedFurnAssetDTO asset -> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(FixedFurnAssetDTO.TYPE);
            }
            case MaintenanceFurnAssetDTO asset -> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(MaintenanceFurnAssetDTO.TYPE);
            }
            case ComputerTechAssetDTO asset -> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(ComputerTechAssetDTO.TYPE);
            }
            case ScreenTechAssetDTO asset -> {
                BeanUtils.copyProperties(assetDTO, assetsItemEntity);
                assetsItemEntity.setAssetType(ScreenTechAssetDTO.TYPE);
            }
            default -> throw new RuntimeException();
        }
        return assetsItemEntity;
    }

}
