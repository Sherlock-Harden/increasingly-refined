package org.practice.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/29 14:12
 **/
@RestController
public class LoginController {


  @GetMapping("/login")
  public Boolean login(@RequestParam String username, @RequestParam String password) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
    subject.login(token);
    return subject.isAuthenticated();
  }
}
