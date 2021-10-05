import config.AdminRealm;
import config.CryptographyRealm;
import config.SystemRealm;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/27 09:18
 **/
@Slf4j
public class TestShiroLogin {

  @Test
  public void oneRealmLogin() {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
    //绑定 realm
    securityManager.setRealm(iniRealm);
    //绑定 securityManager
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("system", "system", false);
    //密码不正确 IncorrectCredentialsException，用户名不正确 UnknownAccountException
    subject.login(token);
    if (subject.isAuthenticated()) {
      log.info("用户[{}]登录成功", subject.getPrincipal());
    } else {
      log.info("用户[{}]登录失败", subject.getPrincipal());
    }
    subject.logout();
  }

  @Test
  public void multiRealmLogin() {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    //设置多realm策略
    ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
    //又一个
    authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
    //authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
    //authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
    securityManager.setAuthenticator(authenticator);
    List<Realm> realms = new LinkedList<>();
    realms.add(new AdminRealm());
    realms.add(new SystemRealm());
    //绑定 realm
    securityManager.setRealms(realms);
    //绑定 securityManager
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("system", "system", false);
    //密码不正确 IncorrectCredentialsException，用户名不正确 UnknownAccountException
    subject.login(token);
    if (subject.isAuthenticated()) {
      log.info("用户[{}]登录成功", subject.getPrincipal());
    } else {
      log.info("用户[{}]登录失败", subject.getPrincipal());
    }
    subject.logout();
  }

  @Test
  public void cryptographyRealmLogin() {
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    //绑定 realm
    securityManager.setRealm(new CryptographyRealm());
    //绑定 securityManager
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("system", "system", false);
    //密码不正确 IncorrectCredentialsException，用户名不正确 UnknownAccountException
    subject.login(token);
    if (subject.isAuthenticated()) {
      log.info("用户[{}]登录成功", subject.getPrincipal());
    } else {
      log.info("用户[{}]登录失败", subject.getPrincipal());
    }
  }

  @Test
  public void hasRole() {

    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
    //绑定 realm
    securityManager.setRealm(iniRealm);
    //绑定 securityManager
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("user", "user", false);
    //密码不正确 IncorrectCredentialsException，用户名不正确 UnknownAccountException
    subject.login(token);
    if (subject.isAuthenticated()) {
      log.info("用户[{}]登录成功", subject.getPrincipal());
    } else {
      log.info("用户[{}]登录失败", subject.getPrincipal());
    }

    if (subject.hasRole("system")) {
      log.info("用户[{}] 拥有[{}]角色", subject.getPrincipal(), "system");
    } else {
      log.error("用户[{}] 未拥有[{}]角色", subject.getPrincipal(), "system");
    }

    if (subject.isPermitted("user:insert")) {
      log.info("用户[{}] 拥有[{}]权限", subject.getPrincipal(), "user:insert");
    } else {
      log.error("用户[{}] 未拥有[{}]权限", subject.getPrincipal(), "user:insert");
    }

  }

}
