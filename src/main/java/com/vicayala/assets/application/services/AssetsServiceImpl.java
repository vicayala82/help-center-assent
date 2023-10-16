package com.vicayala.assets.application.services;

import com.vicayala.assets.application.repositories.AssetRepository;
import com.vicayala.assets.application.services.interfaces.IAssetsService;
import com.vicayala.assets.domain.dtos.asset.AssetDTO;
import com.vicayala.assets.infraestructure.api.vo.asset.AssetVO;
import com.vicayala.assets.infraestructure.db.entities.asset.AssetsItemEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService<AssetVO, String> {

    private final AssetRepository assetRepository;

    @Override
    public Flux<AssetVO> getAll() {
        return assetRepository.findAll()
                .map(AssetsItemEntity::toDTO)
                .map(AssetDTO::toVO);
    }

    @Override
    public Mono<AssetVO> getById(String id) {
        return assetRepository.findById(id)
                .map(AssetsItemEntity::toDTO)
                .map(AssetDTO::toVO);
    }

    @Override
    public Mono<AssetVO> create(AssetVO assetVO) {
        var assetDTO = AssetVO.toDTO(assetVO);
        return assetRepository.save(AssetDTO.ConvertToItemEntity(assetDTO))
                .map(AssetsItemEntity::toDTO)
                .map(AssetDTO::toVO);
    }

    @Override
    public Mono<AssetVO> update(AssetVO assetVO, String id) {
        var assetDTO = AssetVO.toDTO(assetVO);
        assetDTO.setId(id);
        return assetRepository.update(AssetDTO.ConvertToItemEntity(assetDTO))
                .map(AssetsItemEntity::toDTO)
                .map(AssetDTO::toVO);
    }

    @Override
    public Mono<AssetVO> delete(String id) {
        return assetRepository.delete(id)
                .map(AssetsItemEntity::toDTO)
                .map(AssetDTO::toVO);
    }


}
