package com.vicayala.assets.infraestructure.api.configuration;

import com.vicayala.assets.infraestructure.api.handler.AssetsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class RouterFunctionConfig {
    static final String API_ROUTE_URL= "/v2/assets";
    static final String PATH_ID= "/{id}";
    @Bean
    public RouterFunction<ServerResponse> routes(AssetsHandler assetsHandler){
        return route(GET(API_ROUTE_URL), assetsHandler::listAll)
                .andRoute(GET(API_ROUTE_URL + PATH_ID), assetsHandler::getById)
                .andRoute(POST(API_ROUTE_URL), assetsHandler::create)
                .andRoute(PUT(API_ROUTE_URL + PATH_ID), assetsHandler::update)
                .andRoute(DELETE(API_ROUTE_URL + PATH_ID), assetsHandler::delete);
    }
}
