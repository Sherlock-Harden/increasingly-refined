package org.practise.thread;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/10 10:38
 **/
public class T03_sleep_yield_join {

  public static void main(String[] args) {
    //sleep();
    //yield();
    join();
  }


  private static void sleep() {
    for (int i = 0; i < 10; i++) {
      System.out.println("sleep" + i);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 线程 yield 相当于正在执行的线程离开一下，放在一个等待的队列中等待再次被执行，有可能刚进去就又被执行了，也有可能再次轮到它执行。
   */
  private static void yield() {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
      for (int i = 0; i < 50; i++) {
        System.out.println("--------A");
        if (i % 4 == 0) {
          Thread.yield();
        }
      }
    }).start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
      for (int i = 0; i < 50; i++) {
        System.out.println("B--------");
        if (i % 4 == 0) {
          Thread.yield();
        }
      }
    }).start();
  }

  /**
   * T1，T2 两个线程 T2执行过程中调用T1的join方法，会等到T1执行完成后，再继续执行T2
   */
  private static void join() {
    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("A--------");
      }
    });

    Thread t2 = new Thread(() -> {
      System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
      try {
        t1.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      for (int i = 0; i < 10; i++) {
        System.out.println("B--------");
      }
    });
    t2.start();
    t1.start();
  }
}
