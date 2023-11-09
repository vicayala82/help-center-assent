package com.vicayala.assets.application.services;

import com.vicayala.assets.application.repositories.AssetRepository;
import com.vicayala.assets.application.services.interfaces.IAssetsService;
import com.vicayala.assets.domain.dtos.asset.AssetDTO;
import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import com.vicayala.assets.infraestructure.db.entities.asset.AssetsItemEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService<AssetDTO, String> {

    private final AssetRepository assetRepository;

    @Override
    public Flux<AssetDTO> getAll() {
        return assetRepository.findAll()
                .map(AssetsItemEntity::toDTO);
    }

    @Override
    public Mono<AssetDTO> getById(String id) {
        return assetRepository.findById(id)
                .map(AssetsItemEntity::toDTO);
    }

    @Override
    public Mono<AssetDTO> create(AssetDTO assetDTO) {
        return assetRepository.save(AssetDTO.ConvertToItemEntity(assetDTO))
                .map(AssetsItemEntity::toDTO);
    }

    @Override
    public Mono<AssetDTO> update(AssetDTO assetDTO, String id) {
        assetDTO.setId(id);
        return assetRepository.update(AssetDTO.ConvertToItemEntity(assetDTO))
                .map(AssetsItemEntity::toDTO);
    }

    @Override
    public Mono<AssetDTO> delete(String id) {
        return assetRepository.delete(id)
                .map(AssetsItemEntity::toDTO);
    }


}
