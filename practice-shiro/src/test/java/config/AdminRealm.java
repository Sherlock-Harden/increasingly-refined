package config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/27 13:39
 **/
public class AdminRealm implements Realm {

  @Override
  public String getName() {
    return "adminRealm";
  }

  @Override
  public boolean supports(AuthenticationToken authenticationToken) {
    return authenticationToken instanceof UsernamePasswordToken;
  }

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {

    String username = (String) authenticationToken.getPrincipal();
    String password = new String((char[]) authenticationToken.getCredentials());

    if (!"admin".equals(username)) {
      throw new UnknownAccountException();
    }

    if (!"admin".equals(password)) {
      throw new IncorrectCredentialsException();
    }
    return new SimpleAuthenticationInfo(username, password, getName());
  }

}
