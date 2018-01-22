package jaworskg.java.concurrency.c.wrappers;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchMain {

    private static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(), "Task" + i).start();
        }
        latch.await();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " : " + latch.getCount());
        }

    }

}
