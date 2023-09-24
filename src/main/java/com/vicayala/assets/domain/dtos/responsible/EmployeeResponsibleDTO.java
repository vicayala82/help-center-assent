package com.vicayala.assets.domain.dtos.responsible;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class EmployeeResponsibleDTO extends ResponsibleDTO{

    private String fullName;
    private String personalEmail;
    private String documentType;
    private String documentNumber;
    private CompanyDTO company;

}
