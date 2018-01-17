package jaworskg.java.concurrency.b.synchronization;

public class SynchronizedStaticMethodsMain {

    private static final int NUMBER_OF_ITERATIONS = 100000;

    private static int result = 0;
    private static int syncResult = 0;

    private static void increment() {
        result++;
    }

    private static synchronized void syncIncrement() {
        syncResult++;
    }

    public static void main(String[] args) throws InterruptedException {
        Task1 task11 = new Task1();
        Task1 task12 = new Task1();
        Task2 task21 = new Task2();
        Task2 task22 = new Task2();

        Thread t1 = new Thread(task11);
        Thread t2 = new Thread(task12);

        Thread t3 = new Thread(task21);
        Thread t4 = new Thread(task22);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Non-synchronized result is: " + result);
        System.out.println("Non-synchronized task time: " + task11.getTime() + " ms");
        System.out.println("Non-synchronized task time: " + task12.getTime() + " ms");
        System.out.println("Synchronized result is: " + syncResult);
        System.out.println("Synchronized task time: " + task21.getTime() + " ms");
        System.out.println("Synchronized task time: " + task22.getTime() + " ms");
    }

    private static class Task1 implements Runnable {

        private long startTime;
        private long endTime;

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                increment();
            }
            endTime = System.currentTimeMillis();
        }

        private long getTime() {
            return endTime - startTime;
        }

    }

    private static class Task2 implements Runnable {

        private long startTime;
        private long endTime;

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                syncIncrement();
            }
            endTime = System.currentTimeMillis();
        }

        private long getTime() {
            return endTime - startTime;
        }
    }

}
