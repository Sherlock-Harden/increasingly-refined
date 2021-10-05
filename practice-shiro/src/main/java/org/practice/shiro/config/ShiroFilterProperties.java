package org.practice.shiro.config;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/03/11
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "shiro.filter")
public class ShiroFilterProperties {

  private String loginUrl = "/login";

  private String login;

  private String successUrl = "/";

  private String unauthorizedUrl = "/error";

  private String logout;

  private List<String> anon = new LinkedList<>();

  private List<String> authc = new LinkedList<>();

}
