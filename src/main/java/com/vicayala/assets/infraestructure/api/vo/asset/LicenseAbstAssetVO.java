package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vicayala.assets.application.shared.enums.LicenseStatusEnum;
import com.vicayala.assets.domain.dtos.asset.LicenseAbstAssetDTO;
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
@JsonTypeName(LicenseAbstAssetVO.TYPE)
public class LicenseAbstAssetVO extends AbstractAssetVO {

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

    public static LicenseAbstAssetDTO toDTO(LicenseAbstAssetVO licenseAbstAssetVO){
        LicenseAbstAssetDTO licenseAbstAssetDTO = LicenseAbstAssetDTO.builder().build();
        BeanUtils.copyProperties(licenseAbstAssetVO, licenseAbstAssetDTO);
        return licenseAbstAssetDTO;
    }

}
