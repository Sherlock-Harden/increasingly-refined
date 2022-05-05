package org.practice.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/08/06
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    super.configure(resources);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/oauth/**").permitAll()
        .anyRequest().authenticated();
  }
}
