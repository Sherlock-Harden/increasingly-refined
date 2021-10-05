package org.practice.shiro.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/30 10:11
 **/
@Slf4j
public class ShiroSessionListener implements SessionListener {

  @Override
  public void onStart(Session session) {
    log.info("session创建：[{}]", session.getId());
  }

  @Override
  public void onStop(Session session) {
    log.info("session销毁：[{}]", session.getId());
  }


  @Override
  public void onExpiration(Session session) {
    log.info("session过期：[{}]", session.getId());
  }
}
