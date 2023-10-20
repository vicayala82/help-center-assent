package com.vicayala.assets.domain.dtos.responsible;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InHouseEmployeeResponsibleDTO extends EmployeeResponsibleDTO{
    @JsonIgnore
    public static final String TYPE = "in_house";
    @JsonProperty("contract_type")
    private String contractType;
    private String position;
    @JsonProperty("corporate_email")
    private String corporateEmail;
    private Long salary;

}
