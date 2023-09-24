package com.vicayala.assets.domain.dtos.asset;

import com.vicayala.assets.application.shared.enums.AbstractStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AbstractAssetDTO extends AssetDTO{

    private String supplierUrl;
    private AbstractStatusEnum status;

}
