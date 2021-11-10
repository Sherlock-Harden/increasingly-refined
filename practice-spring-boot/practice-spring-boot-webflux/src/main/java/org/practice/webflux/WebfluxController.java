package org.practice.webflux;

import java.util.stream.IntStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/27 09:59
 **/
@RestController
@RequestMapping("/webflux")
public class WebfluxController {

  @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> test() {
    return Flux.fromStream(IntStream.range(1, 10).mapToObj(i -> "i = " + i))
        .doOnSubscribe(subscription -> System.out.println("Subscribe 了～"))
        .doOnComplete(() -> System.out.println("Complete 了～"))
        .doOnNext(data -> System.out.println("有 data 了～"));
  }

}
