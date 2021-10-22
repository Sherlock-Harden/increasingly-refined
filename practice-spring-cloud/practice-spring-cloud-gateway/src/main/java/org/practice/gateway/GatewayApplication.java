package org.practice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/20 16:08
 **/
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  /**
   * 基于springcloud gateway 的路由
   */
  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder rlb) {

    return rlb.routes()
        .route(r -> r.path("/baidu").filters(f -> f.stripPrefix(1)).uri("https://www.baidu.com"))
        .route(r -> r.path("/test-2/**").filters(f -> f.stripPrefix(1)).uri("lb://TEST-1"))
        .build();

  }

  /**
   * 基于 spring webflux 的 reactive 方式的路由
   */
  @Bean
  public RouterFunction<ServerResponse> routerFunction(FluxHandler fluxHandler) {

    RouterFunction<ServerResponse> route = RouterFunctions.route(
        RequestPredicates.path("/reactive"),
        resp -> ServerResponse.ok().body(BodyInserters.fromValue("reactive"))
    ).andRoute(
        RequestPredicates.path("/reactive1"),
        fluxHandler::handle
    );

    return route;
  }


}
