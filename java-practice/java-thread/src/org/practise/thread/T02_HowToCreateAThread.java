package org.practise.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/10 09:50
 **/
public class T02_HowToCreateAThread {

  public static void main(String[] args) {

    new CreateThreat1().start();
    new CreateThread2().run();
    //创建线程的方法三
    new Thread(new CreateThread2()).start();
    new Thread(() -> System.out.println("创建线程的方法四")).start();
    /*
      corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
      maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
      keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
      unit:keepAliveTime的单位
      workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
      threadFactory:线程工厂，用于创建线程，一般用默认即可；
      handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务
    */
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(10));
    poolExecutor.execute(new Thread(() -> System.out.println("创建线程的方式五")
    ));

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new Thread(() -> System.out.println("创建线程的方式六")));
  }

  private static class CreateThreat1 extends Thread {

    @Override
    public void run() {
      System.out.println("创建线程的方法一");
    }
  }

  private static class CreateThread2 implements Runnable {

    @Override
    public void run() {
      System.out.println("创建线程的方法二");
    }
  }
}
