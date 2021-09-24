package org.practice.autoconfiguration;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sherlock[q541458352@126.com]
 * @date 2020/12/01
 **/
@Data
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

  private String[] array;
  private List<Bean> beans;

  @Data
  public static class Bean {

    private String property1;
    private String property2;

    private List<String> strings;

  }
}
