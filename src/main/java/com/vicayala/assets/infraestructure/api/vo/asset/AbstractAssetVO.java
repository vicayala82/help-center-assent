package com.vicayala.assets.infraestructure.api.vo.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vicayala.assets.application.shared.enums.AbstractStatusEnum;
import com.vicayala.assets.domain.dtos.asset.AbstractAssetDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Slf4j
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = LicenseAbstAssetVO.class, name = LicenseAbstAssetVO.TYPE)
})
@JsonTypeName(AbstractAssetVO.TYPE)
public class AbstractAssetVO extends AssetVO {
    @JsonIgnore
    public static final String TYPE = "abstract";

    @JsonProperty("supplier_url")
    private String supplierUrl;
    private String status;

    public void setStatus(String status) {
        AbstractStatusEnum statusEnum;
       try {
           statusEnum = AbstractStatusEnum.valueOf(status);
           this.status = status;
       }catch (IllegalArgumentException e){
          log.error("Status not allowed in AbstractStatusEnum");
       }
    }
    public static AbstractAssetDTO toDTO(AbstractAssetVO abstractAssetVO){
        var abstractAssetDTO = AbstractAssetDTO.builder().build();
        BeanUtils.copyProperties(abstractAssetVO, abstractAssetDTO);
        return abstractAssetDTO;
    }
}
