package org.practice.shiro.controller;

import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/29 15:58
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

  @Resource
  private SessionDAO sessionDAO;


  @RequiresPermissions("user:insert")
  @GetMapping("/permissions")
  public Boolean testPermissions() {
    return true;
  }

  @RequiresRoles({"system"})
  @GetMapping("/roles")
  public Boolean testRoles() {
    return true;
  }

  @GetMapping("/activity")
  public Boolean testActivity() {
    //获取活跃用户非实时
    Collection<Session> sessions = sessionDAO.getActiveSessions();
    for (Session session : sessions) {

      Serializable sessionId = session.getId();
      log.info("sessionId：[{}]", sessionId);
      SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
          .getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
      if (simplePrincipalCollection != null) {
        log.info("PrimaryPrincipal：[{}]", simplePrincipalCollection.getPrimaryPrincipal());
      }
    }
    return true;
  }
}
