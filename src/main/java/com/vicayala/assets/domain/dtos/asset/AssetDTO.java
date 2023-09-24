package com.vicayala.assets.domain.dtos.asset;

import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AssetDTO {

    protected ResponsibleDTO responsibleDTO;
    protected LocalDate purchaseDate;
    protected Long billNumber;
    protected String description;

}
