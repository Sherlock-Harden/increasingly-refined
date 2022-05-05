package org.practice.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/24 09:43
 **/
@RestController
public class LongPollingController {

  @GetMapping(value = "/long/polling", produces = MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=utf-8")
  public String longPolling() {
    System.out.println("请求时间：" + System.currentTimeMillis() + "线程名称：" + Thread.currentThread().getName());
    return "data:" + System.currentTimeMillis() + "\n\n";
  }
}
