package org.practice.sse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/25 11:06
 **/
@RestController
public class SseController {

  @RequestMapping(value = "/test", produces = "text/event-stream;charset=utf-8")
  public String test() {
    return "data:" + System.currentTimeMillis() + "\n\n";
  }
}
