package org.practise.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/04 20:28
 **/
public class UseCountDownLatch {

  public static final int MAX_THREAD = 100;

  public static void main(String[] args) {

    try {
      countDownLatch();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void countDownLatch() throws Exception {
    CountDownLatch latch = new CountDownLatch(MAX_THREAD);

    for (int i = 0; i < MAX_THREAD; i++) {
      new Thread(() -> {
        try {
          System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
          Thread.sleep(3000);
          System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
        } catch (Exception e) {
          e.printStackTrace();
        }
        latch.countDown();
      }).start();
    }
    System.out.println("等待子线程执行完毕...");
    latch.await();
    System.out.println("子线程已经执行完毕");
    System.out.println("继续执行主线程");
  }
}
