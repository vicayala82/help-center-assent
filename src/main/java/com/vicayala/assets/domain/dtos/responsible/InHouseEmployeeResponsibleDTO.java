package com.vicayala.assets.domain.dtos.responsible;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
public class InHouseEmployeeResponsibleDTO extends EmployeeResponsibleDTO{

    private String contractType;
    private String position;
    private String corporateEmail;
    private Long salary;

}
