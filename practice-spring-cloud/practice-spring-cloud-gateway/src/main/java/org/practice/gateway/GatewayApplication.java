package org.practice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/20 16:08
 **/
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder rlb) {

    return rlb.routes()
        .route(r -> r.path("/baidu").filters(f -> f.stripPrefix(1)).uri("https://www.baidu.com"))
        .route(r -> r.path("/test-2/**").filters(f -> f.stripPrefix(1)).uri("lb://TEST-1"))
        .build();

  }
}
