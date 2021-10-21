package org.practice.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sherlock[q541458352@126.com]
 * @since 2020/11/24 20:42
 **/
@SpringBootApplication
public class ListenerApplication implements CommandLineRunner {


  @Value("${maxwell.house}")
  public String s;

  @Value("${server.port}")
  public String s1;

  public static void main(String[] args) {
    SpringApplication.run(ListenerApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println(s);
    System.out.println(s1);
  }
}
