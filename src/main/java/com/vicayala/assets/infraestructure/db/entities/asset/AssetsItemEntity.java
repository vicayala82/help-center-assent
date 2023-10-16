package com.vicayala.assets.infraestructure.db.entities.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vicayala.assets.domain.dtos.asset.AbstractAssetDTO;
import com.vicayala.assets.domain.dtos.asset.AssetDTO;
import com.vicayala.assets.domain.dtos.asset.ComputerTechAssetDTO;
import com.vicayala.assets.domain.dtos.asset.FixedFurnAssetDTO;
import com.vicayala.assets.domain.dtos.asset.LicenseAbstAssetDTO;
import com.vicayala.assets.domain.dtos.asset.MaintenanceFurnAssetDTO;
import com.vicayala.assets.domain.dtos.asset.ScreenTechAssetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamoDbBean
public class AssetsItemEntity {
    @Getter(onMethod=@__({@DynamoDbPartitionKey, @DynamoDbAttribute("id")}))
    private String id;
    @Getter(onMethod=@__({@DynamoDbAttribute("assetType")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String assetType;
    @Getter(onMethod=@__({@DynamoDbAttribute("responsibleId")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responsibleId;
    @Getter(onMethod=@__({@DynamoDbAttribute("purchaseDate")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate purchaseDate;
    @Getter(onMethod=@__({@DynamoDbAttribute("billNumber")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long billNumber;
    @Getter(onMethod=@__({@DynamoDbAttribute("description")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @Getter(onMethod=@__({@DynamoDbAttribute("supplierUrl")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String supplierUrl;
    @Getter(onMethod=@__({@DynamoDbAttribute("status")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @Getter(onMethod=@__({@DynamoDbAttribute("supplierName")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String supplierName;
    @Getter(onMethod=@__({@DynamoDbAttribute("expirationDate")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate expirationDate;
    @Getter(onMethod=@__({@DynamoDbAttribute("manufacturer")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String manufacturer;
    @Getter(onMethod=@__({@DynamoDbAttribute("location")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;
    @Getter(onMethod=@__({@DynamoDbAttribute("quantity")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;
    @Getter(onMethod=@__({@DynamoDbAttribute("detail")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detail;
    @Getter(onMethod=@__({@DynamoDbAttribute("maintenanceResponsible")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String maintenanceResponsible;
    @Getter(onMethod=@__({@DynamoDbAttribute("brand")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;
    @Getter(onMethod=@__({@DynamoDbAttribute("warrantyExpiration")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate warrantyExpiration;
    @Getter(onMethod=@__({@DynamoDbAttribute("warrantyDetail")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String warrantyDetail;
    @Getter(onMethod=@__({@DynamoDbAttribute("processor")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String processor;
    @Getter(onMethod=@__({@DynamoDbAttribute("ram")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ram;
    @Getter(onMethod=@__({@DynamoDbAttribute("hardDrive")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hardDrive;
    @Getter(onMethod=@__({@DynamoDbAttribute("inches")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double inches;

    public static AbstractAssetDTO toAbstractAssetDTO(AssetsItemEntity abstractAssetEntity){
        var abstractAssetDto = AbstractAssetDTO.builder().build();
        BeanUtils.copyProperties(abstractAssetEntity, abstractAssetDto);
        return abstractAssetDto;
    }
    public static LicenseAbstAssetDTO toLicenseAbstAssetDTO(AssetsItemEntity abstractAssetEntity){
        var licenceAbstAssetDto = LicenseAbstAssetDTO.builder().build();
        BeanUtils.copyProperties(abstractAssetEntity, licenceAbstAssetDto);
        return licenceAbstAssetDto;
    }

    public static AssetDTO toDTO(AssetsItemEntity abstractAssetEntity){
        AssetDTO assetDTO = null;
        switch (abstractAssetEntity.getAssetType()){
            case AbstractAssetDTO.TYPE -> assetDTO = new AbstractAssetDTO();
            case LicenseAbstAssetDTO.TYPE -> assetDTO = new LicenseAbstAssetDTO();
            case FixedFurnAssetDTO.TYPE -> assetDTO = new FixedFurnAssetDTO();
            case MaintenanceFurnAssetDTO.TYPE -> assetDTO = new MaintenanceFurnAssetDTO();
            case ComputerTechAssetDTO.TYPE -> assetDTO = new ComputerTechAssetDTO();
            case ScreenTechAssetDTO.TYPE -> assetDTO = new ScreenTechAssetDTO();
        }
        BeanUtils.copyProperties(abstractAssetEntity, assetDTO);
        return assetDTO;
    }
}
