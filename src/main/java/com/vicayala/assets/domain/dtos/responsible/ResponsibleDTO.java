package com.vicayala.assets.domain.dtos.responsible;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Data
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContractEmployeeResponsibleDTO.class, name = ContractEmployeeResponsibleDTO.TYPE),
        @JsonSubTypes.Type(value = InHouseEmployeeResponsibleDTO.class, name = InHouseEmployeeResponsibleDTO.TYPE),
        @JsonSubTypes.Type(value = BranchResponsibleDTO.class, name = BranchResponsibleDTO.TYPE)
})
public abstract class ResponsibleDTO {

}
