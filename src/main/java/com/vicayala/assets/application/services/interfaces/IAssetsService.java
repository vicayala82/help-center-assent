package com.vicayala.assets.application.services.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAssetsService <T,ID>{

    Flux<T> getAll ();
    Mono<T> getById (ID id);
    Mono<T> create(T assetVO);
    Mono<T> update(T assetVO, String id);
    Mono<T> delete(ID id);

}
