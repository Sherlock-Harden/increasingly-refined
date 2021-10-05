package org.practice.shiro.config;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.practice.shiro.realm.UsernamePasswordLoginRealm;
import org.springframework.context.annotation.Bean;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/29 13:23
 **/
public class ShiroConfig {

  @Resource
  private ShiroFilterProperties shiroFilterProperties;

  @Resource
  private SecurityManager securityManager;

  @Bean
  public ShiroFilterFactoryBean shiroFilter() {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager);
    //登录
    shiroFilter.setLoginUrl(shiroFilterProperties.getLoginUrl());

    //首页
    shiroFilter.setSuccessUrl(shiroFilterProperties.getSuccessUrl());
    //错误页面，认证不通过跳转
    shiroFilter.setUnauthorizedUrl(shiroFilterProperties.getUnauthorizedUrl());
    Map<String, String> map = new LinkedHashMap<>();
    map.put(shiroFilterProperties.getLogout(), "logout");
    map.put(shiroFilterProperties.getLoginUrl(), "anon");
    map.put(shiroFilterProperties.getLogin(), "anon");
    shiroFilterProperties.getAnon().forEach(path -> map.put(path, "anon"));
    shiroFilterProperties.getAuthc().forEach(path -> map.put(path, "authc"));
    shiroFilter.setFilterChainDefinitionMap(map);
    return shiroFilter;
  }

  @Bean
  public SecurityManager securityManager(SessionManager sessionManager) {
    DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

    //设置realm匹配策略
    ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
    authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
    webSecurityManager.setAuthenticator(authenticator);

    List<Realm> realms = new LinkedList<>();
    realms.add(new UsernamePasswordLoginRealm());
    //realms.add(new PhonePasswordLoginRealm());

    //设置多realms
    webSecurityManager.setRealms(realms);

    webSecurityManager.setSessionManager(sessionManager);
    //webSecurityManager.setRememberMeManager();
    //webSecurityManager.setCacheManager();
    return webSecurityManager;
  }

  @Bean("sessionManager")
  public SessionManager sessionManager(SessionDAO sessionDAO) {
    DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();

    //设置session过期时间
    webSessionManager.setGlobalSessionTimeout(30 * 60 * 1000);
    webSessionManager.setSessionValidationSchedulerEnabled(true);
    webSessionManager.setSessionIdUrlRewritingEnabled(false);
    webSessionManager.setDeleteInvalidSessions(true);

    SimpleCookie simpleCookie = new SimpleCookie();
    simpleCookie.setName("abcd");
    webSessionManager.setSessionIdCookie(simpleCookie);

    webSessionManager.setSessionDAO(sessionDAO);
    return webSessionManager;
  }

  @Bean("sessionDAO")
  public SessionDAO sessionDAO() {
    return new MemorySessionDAO();
  }


  /**
   * 开启shiro 注解支持
   *
   * @return AuthorizationAttributeSourceAdvisor
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

}
