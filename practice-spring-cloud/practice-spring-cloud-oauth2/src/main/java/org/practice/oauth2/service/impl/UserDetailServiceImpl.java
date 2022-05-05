package org.practice.oauth2.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/08/06
 **/
@Service(value = "UserDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    //权限集合
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    return new User("admin", "123456", authorities);
  }
}
