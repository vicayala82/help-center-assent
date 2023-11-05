package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsibleVO {
    @JsonIgnore
    private String id;
    @JsonProperty("personal_email")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String personalEmail;
    @JsonProperty("full_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullName;
    @JsonProperty("company")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String company;
    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonProperty("city")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String city;

}
