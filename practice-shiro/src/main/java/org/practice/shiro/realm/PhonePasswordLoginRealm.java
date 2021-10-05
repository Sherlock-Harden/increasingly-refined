package org.practice.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/27 15:03
 **/
public class PhonePasswordLoginRealm extends AuthenticatingRealm {

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    return new SimpleAuthenticationInfo("13809876543", "ed1b2f44663592373d64eb59e82040e8",
        ByteSource.Util.bytes("salt"),
        getName());
  }

  @Override
  public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    hashedCredentialsMatcher.setHashAlgorithmName("MD5");
    hashedCredentialsMatcher.setHashIterations(1);
    hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
    super.setCredentialsMatcher(hashedCredentialsMatcher);
  }
}
