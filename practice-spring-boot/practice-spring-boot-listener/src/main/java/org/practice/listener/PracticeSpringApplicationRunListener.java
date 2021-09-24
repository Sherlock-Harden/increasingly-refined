package org.practice.listener;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @author sherlock[q541458352@126.com]
 * @date 2020/11/27
 **/
public class PracticeSpringApplicationRunListener extends EventPublishingRunListener {


  public PracticeSpringApplicationRunListener(SpringApplication application, String[] args) {
    super(application, args);
  }

  /**
   * 读取配置文件内容 放到springboot容器
   */
  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {

    Properties properties = new Properties();
    try {
      //读取文件
      properties.load(this.getClass().getClassLoader().getResourceAsStream("practice.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    //读取名称为 *** 的内容
    PropertySource<Map<String, Object>> propertySource = new PropertiesPropertySource("maxwell", properties);
    /**
     * 将资源添加到springboot中
     */
    MutablePropertySources propertySources = environment.getPropertySources();
    propertySources.addLast(propertySource);
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {

  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {

  }

  /**
   * springboot容器启动成功的回调
   */
  @Override
  public void started(ConfigurableApplicationContext context) {
    System.out.println("springboot容器启动成功 一定会到这来。");
  }

  @Override
  public void running(ConfigurableApplicationContext context) {
    System.out.println("springboot容器可以运行 一定会到这来。");
  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {
    System.out.println("springboot容器启动失败 一定会到这来。");
  }

  /**
   * 文件加载优先级 越小优先级越高
   */
  @Override
  public int getOrder() {
    return 0;
  }

}
