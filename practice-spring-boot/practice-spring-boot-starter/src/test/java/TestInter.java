import java.util.concurrent.CountDownLatch;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @date 2021/09/02
 **/
public class TestInter {

  private static final int MAX_THREAD = 20;

  public static void main(String[] args) {
    final CountDownLatch latch = new CountDownLatch(MAX_THREAD);

    for (int i = 0; i < MAX_THREAD; i++) {
      new Thread(() -> {
        try {
          System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
          Thread.sleep(3000);
          System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
          latch.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    try {
      System.out.println("等待子线程执行完毕...");
      latch.await();
      System.out.println("子线程已经执行完毕");
      System.out.println("继续执行主线程");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
