package com.vicayala.assets.domain.dtos.responsible;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private String name;
    private String nit;
    private String location;
}
