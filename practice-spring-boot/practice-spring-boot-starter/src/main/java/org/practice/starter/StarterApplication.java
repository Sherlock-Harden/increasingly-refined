package org.practice.starter;

import javax.annotation.Resource;
import org.practice.starter.service.PracticeStarterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sherlock[q541458352@126.com]
 * @since 2020/11/24 20:42
 **/
@SpringBootApplication
public class StarterApplication implements CommandLineRunner {

  @Resource
  private PracticeStarterService practiceStarterService;

  public static void main(String[] args) {
    SpringApplication.run(StarterApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println(practiceStarterService.sout());
  }
}
