package jaworskg.java.concurrency.c.wrappers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CyclicBarrierMain {

    private static final int NUMBER_OF_THREADS = 5;

    private static CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS,
            () -> System.out.println("Cyclic barrier reached"));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            new Thread(new Task()).start();
        }
        System.out.println();
        Thread.sleep(1000);
        barrier.reset();
        for (int i = 0; i < NUMBER_OF_THREADS - 1; i++) {
            new Thread(new Task()).start();
        }
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Number of waiting threads: " + barrier.getNumberWaiting());
                barrier.await(1, SECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                System.out.println("Exception has been thrown: " + e.getClass().getSimpleName());
            }
        }

    }

}
