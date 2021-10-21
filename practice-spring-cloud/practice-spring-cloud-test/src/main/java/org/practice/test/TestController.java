package org.practice.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/21 14:49
 **/
@RestController
public class TestController {

  @GetMapping("/test")
  public String test() {
    return "test";
  }
}
