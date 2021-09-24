package org.practise.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/10 09:03
 **/
public class T01_WhatIsThread {

  /**
   * 小测试 jdk8 结论
   * 调用线程的run()方法， 线程执行 main()->run()->main()
   * 调用线程的start()方法，main()执行同时 和 start() 同时执行
   *
   * 小测试 jdk11 结论
   * 不明。。。。。为啥 start() 不交替了。
   */
  public static void main(String[] args) {

    //new Thread1().run();
    new Thread1().start();
    for (int i = 0; i < 10; i++) {
      try {
        TimeUnit.MICROSECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("main");
    }
  }

  private static class Thread1 extends Thread {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread1" + i);
      }
    }
  }
}
