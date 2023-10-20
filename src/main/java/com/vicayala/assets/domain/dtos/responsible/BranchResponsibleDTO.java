package com.vicayala.assets.domain.dtos.responsible;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponsibleDTO extends ResponsibleDTO{
    @JsonIgnore
    public static final String TYPE = "branch";
    private String name;
    private String city;
    private String address;
}
