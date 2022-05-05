package org.practice.oauth2.config;

import java.util.Objects;
import javax.annotation.Resource;
import org.practice.oauth2.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/08/05
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Resource
  private UserDetailServiceImpl userDetailService;

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().authorizeRequests().antMatchers("/oauth/**").permitAll().anyRequest().authenticated()
        //授权码需要配置
        .and().httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return charSequence.toString();
      }

      @Override
      public boolean matches(CharSequence charSequence, String s) {
        return Objects.equals(charSequence, s);
      }
    });
  }
}
