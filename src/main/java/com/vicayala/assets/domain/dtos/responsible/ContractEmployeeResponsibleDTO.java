package com.vicayala.assets.domain.dtos.responsible;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
public class ContractEmployeeResponsibleDTO extends EmployeeResponsibleDTO{
    private String serviceDescription;
    private LocalDate endedDateService;
    private BigDecimal serviceTotalPayment;
    private String currency;
}
