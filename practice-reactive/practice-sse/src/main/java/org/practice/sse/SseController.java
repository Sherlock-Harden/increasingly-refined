package org.practice.sse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/25 11:06
 **/
@RestController
@RequestMapping("/sse")
public class SseController {

  public static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();


  @GetMapping("/subscribe")
  public SseEmitter subscribe(String id) {
    //设置超时时间
    SseEmitter sseEmitter = new SseEmitter(3600000L);
    sseCache.put(id, sseEmitter);
    //超时处理
    sseEmitter.onTimeout(() -> sseCache.remove(id));
    //结束之后的回调触发
    sseEmitter.onCompletion(() -> System.out.println("回调完成"));
    return sseEmitter;
  }

  @GetMapping("/published")
  public String published(String id, String content) throws IOException {
    SseEmitter sseEmitter = sseCache.get(id);
    if (sseEmitter != null) {
      sseEmitter.send(content);
    }
    return "Completion";
  }

  @RequestMapping(value = "/test", produces = "text/event-stream;charset=utf-8")
  public String test() {
    return "data:" + System.currentTimeMillis() + "\n\n";
  }
}
