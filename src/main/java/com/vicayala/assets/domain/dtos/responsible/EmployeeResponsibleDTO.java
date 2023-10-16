package com.vicayala.assets.domain.dtos.responsible;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public abstract class EmployeeResponsibleDTO extends ResponsibleDTO{

    private String fullName;
    private String personalEmail;
    private String documentType;
    private String documentNumber;
    private CompanyDTO company;

}
