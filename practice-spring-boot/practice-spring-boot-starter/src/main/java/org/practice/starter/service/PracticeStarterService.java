package org.practice.starter.service;

import lombok.Data;
import org.practice.starter.config.PracticeStarterProperties;

/**
 * @author sherlock[q541458352@126.com]
 * @since 2020/11/24 20:55
 **/
@Data
public class PracticeStarterService {

  private PracticeStarterProperties practiceStarterProperties;

  public String sout() {
    return practiceStarterProperties.toString();
  }

}
