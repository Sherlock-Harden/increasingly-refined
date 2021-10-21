package org.practice.gateway;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import java.util.List;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/21 16:07
 **/
public class CustomizeLoadBalancerRule extends AbstractLoadBalancerRule {

  @Override
  public void initWithNiwsConfig(IClientConfig iClientConfig) {

  }

  @Override
  public Server choose(Object o) {
    //#获取可以到达的服务
    List<Server> reachableServers = this.getLoadBalancer().getReachableServers();
    //自己写负载均衡策略
    int size = reachableServers.size();
    int a = (int) (Math.random() * 10) % size;
    System.out.println("a >>>>>>>>>>>>>>>>>>>> " + a);
    return reachableServers.get(a);
  }
}
