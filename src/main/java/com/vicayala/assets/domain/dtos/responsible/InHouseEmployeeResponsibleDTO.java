package com.vicayala.assets.domain.dtos.responsible;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InHouseEmployeeResponsibleDTO extends EmployeeResponsibleDTO{

    private String contractType;
    private String position;
    private String corporateEmail;
    private Long salary;

}
