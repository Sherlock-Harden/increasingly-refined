package org.practice.gateway;

import java.util.List;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/21 16:38
 **/
@Component
public class CustomizeFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    ServerHttpRequest request = exchange.getRequest();

    MultiValueMap<String, String> queryParams = request.getQueryParams();
    List<String> list = queryParams.get("xxx");

    if (list == null || list.isEmpty()) {
      return exchange.getResponse().setComplete();
    }

    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
