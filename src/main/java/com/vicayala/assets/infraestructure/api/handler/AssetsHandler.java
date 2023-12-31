package com.vicayala.assets.infraestructure.api.handler;

import com.vicayala.assets.application.services.AssetsServiceImpl;
import com.vicayala.assets.domain.dtos.asset.AssetDTO;
import com.vicayala.assets.infraestructure.api.vo.asset.AssetVO;
import com.vicayala.assets.infraestructure.api.vo.asset.ResponsibleVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@AllArgsConstructor
@Slf4j
public class AssetsHandler {

    private final AssetsServiceImpl assetsService;
    private final ResponsibleHandler responsibleHandler;
    public Mono<ServerResponse> listAll(ServerRequest request){
        Flux<AssetVO> assetVOFlux = assetsService.getAll().map(AssetDTO::toVO);
        Flux<ResponsibleVO> responsibleVOFlux = assetVOFlux.map(assetVO -> {
            if(Objects.nonNull(assetVO.getResponsibleId())){
                return responsibleHandler.createResponsibleVO(assetVO.getResponsibleId());
            }
            return ResponsibleVO.builder().build();
        });
        Flux<AssetVO> responseAssets = assetVOFlux.zipWith(responsibleVOFlux,
                (assetVO, responsibleVO) ->{
                    if(Objects.nonNull(assetVO.getResponsibleId()) &&
                            Objects.nonNull(responsibleVO) &&
                            assetVO.getResponsibleId().equals(responsibleVO.getId())){
                        assetVO.setResponsible(responsibleVO);
                        return assetVO;
                    }
                    return assetVO;
                });
        return ServerResponse.ok()
                .body(responseAssets,AssetVO.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request){
        String id = request.pathVariable("id");
        return assetsService.getById(id)
                .map(AssetDTO::toVO)
                .flatMap(assetVO -> ServerResponse.ok()
                .body(fromValue(assetVO)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<AssetVO> assetInDb = assetsService.getById(id).map(AssetDTO::toVO);

        return assetInDb.flatMap(assetVO -> ServerResponse.ok()
                    .body(assetsService.delete(id).map(AssetDTO::toVO), AssetVO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<AssetDTO> assetDTO = request.bodyToMono(AssetVO.class)
                .map(AssetVO::toDTO);
        return assetDTO.flatMap(assetsService::create).map(AssetDTO::toVO)
                .flatMap(assetResponse -> ServerResponse
                        .created(URI.create("/v2/assets/"+assetResponse.getId()))
                    .body(fromValue(assetResponse)));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<AssetDTO> assetRequestDTO = request.bodyToMono(AssetVO.class)
                .map(AssetVO::toDTO);
        String id = request.pathVariable("id");
        Mono<AssetDTO> assetInDb = assetsService.getById(id);

        return assetInDb.zipWith(assetRequestDTO, (db, req) ->{
                BeanUtils.copyProperties(req,db);
                db.setId(req.getId());
                return db;
            }).flatMap(assetToPersist -> ServerResponse.ok()
                .body(assetsService.update(assetToPersist, id).map(AssetDTO::toVO),
                        AssetVO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
