package jaworskg.java.concurrency.c.wrappers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicClassesMain {

    private static final int MAX_NUMBER_OF_ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {
        final SharedBox sharedBox = new SharedBox();

        Task1 task1 = new Task1(sharedBox);
        Task2 task2 = new Task2(sharedBox);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);
        Thread t3 = new Thread(task2);
        Thread t4 = new Thread(task2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Atomic integer is: " + sharedBox.atomicInteger);
        System.out.println("Atomic long is: " + sharedBox.atomicLong);

        System.out.println("Raw integer is: " + sharedBox.rawInteger);
        System.out.println("Raw long is: " + sharedBox.rawLong);
    }

    private static class SharedBox {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicLong atomicLong = new AtomicLong(0);

        int rawInteger = 0;
        long rawLong = 0;

        private void incrementAtomics() {
            atomicInteger.incrementAndGet();
            atomicLong.incrementAndGet();
        }

        private void incrementRaws() {
            rawInteger++;
            rawLong++;
        }
    }

    private static class Task1 implements Runnable {

        private final SharedBox sharedBox;

        private Task1(SharedBox sharedBox) {
            this.sharedBox = sharedBox;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_NUMBER_OF_ITERATIONS; i++) {
                sharedBox.incrementAtomics();
            }
        }

    }

    private static class Task2 implements Runnable {

        private final SharedBox sharedBox;

        private Task2(SharedBox sharedBox) {
            this.sharedBox = sharedBox;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_NUMBER_OF_ITERATIONS; i++) {
                sharedBox.incrementRaws();
            }
        }

    }

}
