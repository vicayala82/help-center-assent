package com.vicayala.assets.infraestructure.api.handler;

import com.vicayala.assets.application.services.AssetsServiceImpl;
import com.vicayala.assets.infraestructure.api.vo.asset.AssetVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@AllArgsConstructor
public class AssetsHandler {

    private final AssetsServiceImpl assetsService;
    public Mono<ServerResponse> listAll(ServerRequest request){
        return ServerResponse.ok()
                .body(assetsService.getAll(), AssetVO.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request){
        String id = request.pathVariable("id");
        return assetsService.getById(id)
                .flatMap(assetVO -> ServerResponse.ok()
                .body(fromValue(assetVO)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<AssetVO> assetInDb = assetsService.getById(id);

        return assetInDb.flatMap(assetVO -> ServerResponse.ok()
                    .body(assetsService.delete(id), AssetVO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<AssetVO> assetVO = request.bodyToMono(AssetVO.class);
        return assetVO.flatMap(assetsService::create)
                .flatMap(assetResponse -> ServerResponse.created(URI.create("/v2/assets/"+assetResponse.getId()))
                    .body(fromValue(assetResponse)));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<AssetVO> assetRequestVO = request.bodyToMono(AssetVO.class);
        String id = request.pathVariable("id");
        Mono<AssetVO> assetInDb = assetsService.getById(id);

        return assetInDb.zipWith(assetRequestVO, (db, req) ->{
                BeanUtils.copyProperties(req,db);
                db.setId(req.getId());
                return db;
            }).flatMap(assetToPersist -> ServerResponse.ok()
                .body(assetsService.update(assetToPersist, id), AssetVO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
