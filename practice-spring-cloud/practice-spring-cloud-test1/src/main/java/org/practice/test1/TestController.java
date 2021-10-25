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

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/test")
  public String test() {
    return "当前应用" + appName + "  的端口是：" + port;
  }
}
