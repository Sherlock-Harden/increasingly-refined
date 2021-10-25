package org.practice.gateway;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 响应式路由处理
 *
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/22 10:16
 **/
@Component
public class FluxHandler implements HandlerFunction<ServerResponse> {

  /**
   * 注意 ⚠️ 不会将请求继续向后传递
   */
  @Override
  public Mono<ServerResponse> handle(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromValue("响应式返回字符串"));
  }


}
