package com.vicayala.assets.domain.dtos.asset;

import com.vicayala.assets.application.shared.enums.LicenseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseAbstAssetDTO extends AbstractAssetDTO{

    private String supplierName;
    private LocalDate expirationDate;
    private LicenseStatusEnum status;

}
