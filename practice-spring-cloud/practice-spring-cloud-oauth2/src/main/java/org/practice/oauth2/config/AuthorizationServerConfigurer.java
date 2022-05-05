package org.practice.oauth2.config;

import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/08/05
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

  @Resource
  private TokenStore tokenStore;

  @Resource
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        //客户端ID
        .withClient("demo-client")
        //客户端密钥
        .secret("demo-client-secret-123456")
        //授权同意的类型
        .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
        //作用域，范围
        .scopes("all")
        //有效时间
        .accessTokenValiditySeconds(1800)
        //授权码模式配置
        .redirectUris("www.baidu.com")
        .autoApprove(true)
    ;

  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        //token存储方式
        .tokenStore(tokenStore)
        // 用户信息
        //.userDetailsService(UserDetailService)
        //身份验证管理
        .authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .passwordEncoder(new PasswordEncoder() {
          @Override
          public String encode(CharSequence charSequence) {
            return charSequence.toString();
          }

          @Override
          public boolean matches(CharSequence charSequence, String s) {
            return Objects.equals(charSequence, s);
          }
        })
        /*
          如果配置支持allowFormAuthenticationForClients的，
          且对/oauth/token请求的参数中有client_id和client-secret的会走ClientCredentialsTokenEndpointFilter来保护
          如果没有配置支持allowFormAuthenticationForClients
          或者
          有支持但对/oauth/token请求的参数中没有client_id和client_secret的，走basic认证保护
         */
        .allowFormAuthenticationForClients();
  }

  @Bean
  public TokenStore tokenStore() {
    //存储方式
    return new InMemoryTokenStore();
  }
}
