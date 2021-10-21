package org.practice.test1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/21 14:49
 **/
@RestController
public class TestController {

  @Value("${server.port}")
  private int port;

  @GetMapping("/test")
  public String test() {
    return "当前 test 的端口是：" + port;
  }
}
