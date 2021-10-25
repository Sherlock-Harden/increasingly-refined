package org.practice.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义限流规则
 *
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/22 14:30
 **/
@Component
public class RateLimitConfig {

  @Bean
  public KeyResolver customizeKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
  }

}
