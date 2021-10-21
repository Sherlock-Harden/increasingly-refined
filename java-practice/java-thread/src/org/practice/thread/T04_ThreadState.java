package org.practice.thread;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/19 14:18
 **/


public class T04_ThreadState {

  public static void main(String[] args) {
    Thread thread = new MyThread();

    System.out.println(thread.getState());

    thread.start();

    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(thread.getState());
  }

  static class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println(this.getState());
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(i);
      }
    }
  }
}
