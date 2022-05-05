package org.practice.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/08/06
 **/
@RestController
public class ApplicationController {

  @GetMapping("/application")
  public String application() {
    return "success";
  }
}
