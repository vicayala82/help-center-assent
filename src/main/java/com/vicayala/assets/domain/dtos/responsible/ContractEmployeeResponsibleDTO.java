package com.vicayala.assets.domain.dtos.responsible;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContractEmployeeResponsibleDTO extends EmployeeResponsibleDTO{
    @JsonIgnore
    public static final String TYPE = "contract";
    @JsonProperty("service_description")
    private String serviceDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("ended_date_service")
    private LocalDate endedDateService;
    @JsonProperty("service_total_payment")
    private BigDecimal serviceTotalPayment;
    private String currency;
}
