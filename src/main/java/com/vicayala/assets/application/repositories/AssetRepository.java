package com.vicayala.assets.application.repositories;

import com.vicayala.assets.infraestructure.db.entities.asset.AssetsItemEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AssetRepository{

    public static final String TABLE_NAME = "help-center-asset";
    private final DynamoDbAsyncTable<AssetsItemEntity> assetTable;

    private Key getKeyBuild(String id) {
        return Key.builder().partitionValue(id).build();
    }
    public AssetRepository(DynamoDbEnhancedAsyncClient dynamoClient){

        assetTable = dynamoClient.table(TABLE_NAME, TableSchema.fromBean(AssetsItemEntity.class));
    }

    public Flux<AssetsItemEntity> findAll() {
        return Flux.from(assetTable.scan().items());
    }
    public Mono<AssetsItemEntity> findById(String id) {
        return Mono.fromFuture(assetTable.getItem(getKeyBuild(id)));
    }
    public Mono<AssetsItemEntity> delete(String id) {
        return Mono.fromCompletionStage(assetTable.deleteItem(getKeyBuild(id)));
    }

    public Mono<Integer> count() {
        ScanEnhancedRequest scanEnhancedRequest = ScanEnhancedRequest.builder().addAttributeToProject("id").build();
        AtomicInteger counter = new AtomicInteger(0);
        return Flux.from(assetTable.scan(scanEnhancedRequest))
                .doOnNext(page -> counter.getAndAdd(page.items().size()))
                .then(Mono.defer(() -> Mono.just(counter.get())));
    }

    public Mono<AssetsItemEntity> update(AssetsItemEntity entity) {
        var updateRequest = UpdateItemEnhancedRequest.builder(AssetsItemEntity.class).item(entity).build();
        return Mono.fromCompletionStage(assetTable.updateItem(updateRequest));
    }

    public Mono<AssetsItemEntity> save(AssetsItemEntity entity){
        entity.setId(UUID.randomUUID().toString());
        var putRequest = PutItemEnhancedRequest.builder(AssetsItemEntity.class).item(entity).build();
        return Mono.fromCompletionStage(assetTable.putItem(putRequest).thenApply(x -> entity));
    }

}
