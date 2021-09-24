package org.practice.autoconfiguration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sherlock[q541458352@126.com]
 * @date 2020/12/01
 **/
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class ConfigAutoConfiguration {

}
