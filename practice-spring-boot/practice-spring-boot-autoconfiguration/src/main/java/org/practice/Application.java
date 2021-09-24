package org.practice;

import javax.annotation.Resource;
import org.practice.autoconfiguration.ConfigProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sherlock[q541458352@126.com]
 * @date 2020/12/01
 **/
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Resource
  private ConfigProperties configProperties;


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(configProperties.toString());
  }
}
