package com.vicayala.assets.domain.dtos.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicayala.assets.application.shared.enums.AbstractStatusEnum;
import com.vicayala.assets.infraestructure.api.vo.asset.AbstractAssetVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Slf4j
public class AbstractAssetDTO extends AssetDTO{
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

    public static AbstractAssetVO toVO(AbstractAssetDTO abstractAssetDTO){
        var abstractAssetVO = AbstractAssetVO.builder().build();
        BeanUtils.copyProperties(abstractAssetDTO, abstractAssetVO);
        return abstractAssetVO;
    }
}
