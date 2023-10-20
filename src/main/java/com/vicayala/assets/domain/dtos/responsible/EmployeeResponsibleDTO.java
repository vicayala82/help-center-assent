package com.vicayala.assets.domain.dtos.responsible;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class EmployeeResponsibleDTO extends ResponsibleDTO{

    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("personal_email")
    private String personalEmail;
    @JsonProperty("document_type")
    private String documentType;
    @JsonProperty("document_number")
    private String documentNumber;
    private CompanyDTO company;

}
