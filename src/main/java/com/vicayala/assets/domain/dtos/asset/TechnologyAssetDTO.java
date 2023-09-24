package com.vicayala.assets.domain.dtos.asset;

import com.vicayala.assets.application.shared.enums.TechnologyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class TechnologyAssetDTO extends AssetDTO {

    private String brand;
    private String location;
    private String detail;
    private LocalDate warrantyExpiration;
    private String warrantyDetail;
    private TechnologyStatusEnum status;


}
