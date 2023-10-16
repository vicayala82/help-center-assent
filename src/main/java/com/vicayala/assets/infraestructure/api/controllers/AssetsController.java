package com.vicayala.assets.infraestructure.api.controllers;

import com.vicayala.assets.application.services.AssetsServiceImpl;
import com.vicayala.assets.infraestructure.api.vo.asset.AssetVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/assets")
@Slf4j
@AllArgsConstructor
public class AssetsController {

    private final AssetsServiceImpl assetsService;

    @GetMapping
    public Mono<ResponseEntity<Flux<AssetVO>>> getAll(){
        return Mono.just(
                ResponseEntity.ok(assetsService.getAll())
        );
    }
    @PostMapping
    public Mono<ResponseEntity<Mono<AssetVO>>>  create(@RequestBody AssetVO asset){
        return Mono.just(ResponseEntity.ok(assetsService.create(asset)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<AssetVO>>> get(@PathVariable String id){
        return Mono.just(ResponseEntity.ok(assetsService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Mono<AssetVO>>> delete(@PathVariable String id){
        return Mono.just(ResponseEntity.ok(assetsService.delete(id)));
    }

}
