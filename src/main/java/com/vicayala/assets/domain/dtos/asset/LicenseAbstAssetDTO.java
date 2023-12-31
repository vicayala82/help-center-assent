package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicayala.assets.application.shared.enums.LicenseStatusEnum;
import com.vicayala.assets.infraestructure.api.vo.asset.LicenseAbstAssetVO;
import com.vicayala.assets.infraestructure.db.entities.asset.AssetsItemEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@Slf4j
public class LicenseAbstAssetDTO extends AbstractAssetDTO{

    @JsonIgnore
    public static final String TYPE = "license_abstract";
    @JsonProperty("supplier_name")
    private String supplierName;
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;
    private String status;

    @Override
    public void setStatus(String status) {
        LicenseStatusEnum statusEnum;
        try {
            statusEnum = LicenseStatusEnum.valueOf(status);
            this.status = status;
        }catch (IllegalArgumentException e){
            log.error("Status not allowed in AbstractStatusEnum");
        }
    }

    public static AssetsItemEntity toEntity(LicenseAbstAssetDTO licenseAbstAssetDTO){
        AssetsItemEntity assetsItemEntity = AssetsItemEntity.builder().build();
        BeanUtils.copyProperties(licenseAbstAssetDTO, assetsItemEntity);
        assetsItemEntity.setAssetType(LicenseAbstAssetDTO.TYPE);
        return assetsItemEntity;
    }

    public static LicenseAbstAssetVO toVO(LicenseAbstAssetDTO licenseAbstAssetDTO){
        LicenseAbstAssetVO licenseAbstAssetVO = LicenseAbstAssetVO.builder().build();
        BeanUtils.copyProperties(licenseAbstAssetDTO, licenseAbstAssetVO);
        return licenseAbstAssetVO;
    }

}
