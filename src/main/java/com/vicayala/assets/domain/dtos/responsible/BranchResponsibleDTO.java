package com.vicayala.assets.domain.dtos.responsible;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
public class BranchResponsibleDTO extends ResponsibleDTO{

    private String name;
    private String city;
    private String address;
}
