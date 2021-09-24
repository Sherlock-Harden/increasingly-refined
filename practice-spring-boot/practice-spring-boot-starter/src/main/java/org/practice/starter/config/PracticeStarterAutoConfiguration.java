package org.practice.starter.config;

import javax.annotation.Resource;
import org.practice.starter.service.PracticeStarterService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sherlock[q541458352@126.com]
 * @since 2020/11/24 20:44
 **/
@Configuration
@EnableConfigurationProperties(PracticeStarterProperties.class)
public class PracticeStarterAutoConfiguration {

  @Resource
  private PracticeStarterProperties properties;

  @Bean
  public PracticeStarterService practiceStarterService() {
    PracticeStarterService practiceStarterService = new PracticeStarterService();
    practiceStarterService.setPracticeStarterProperties(properties);
    return practiceStarterService;
  }
}
