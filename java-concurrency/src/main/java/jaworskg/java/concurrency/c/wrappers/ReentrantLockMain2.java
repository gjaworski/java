package jaworskg.java.concurrency.c.wrappers;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain2 {

    private static final long MAX_TIME = 2000;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();

        Task1 task1 = new Task1(reentrantLock);
        Task2 task2 = new Task2(reentrantLock);
        Task3 task3 = new Task3(reentrantLock);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        System.out.println("Thread 1 state: " + t1.getState());
        System.out.println("Thread 2 state: " + t2.getState());
        System.out.println("Thread 3 state: " + t3.getState());

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }

    private static class Task1 implements Runnable {

        private final ReentrantLock lock;

        private Task1(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() - time < MAX_TIME) {
                }
                System.out.println("Task 1: lock isHeldByCurrentThread: " + lock.isHeldByCurrentThread());
            } finally {
                lock.unlock();
            }
        }

    }

    private static class Task2 implements Runnable {

        private final ReentrantLock lock;

        private Task2(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            boolean interrupted = false;
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                interrupted = true;
                System.out.println("Thread has been interrupted while waiting for lock.");
            }
            System.out.println("Task 2: isHeldByCurrentThread: " + lock.isHeldByCurrentThread());
            if (!interrupted) {
                lock.unlock();
            }
        }

    }

    private static class Task3 implements Runnable {

        private final ReentrantLock lock;

        private Task3(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("Task 3: lock isHeldByCurrentThread: " + lock.isHeldByCurrentThread());
            } finally {
                lock.unlock();
            }
        }

    }

}
