package jaworskg.java.concurrency.b.synchronization;

public class SynchronizedInstanceMethodsMain {

    private static int NUMBER_OF_ITERATIONS = 10000000;

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();

        Task1 task11 = new Task1(worker);
        Task1 task12 = new Task1(worker);
        Task2 task21 = new Task2(worker);
        Task2 task22 = new Task2(worker);

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

        System.out.println("Non-synchronized result is: " + worker.result);
        System.out.println("Non-synchronized task time: " + task11.getTime() + " ms");
        System.out.println("Non-synchronized task time: " + task12.getTime() + " ms");
        System.out.println("Synchronized result is: " + worker.syncResult);
        System.out.println("Synchronized task time: " + task21.getTime() + " ms");
        System.out.println("Synchronized task time: " + task22.getTime() + " ms");
    }

    private static class Worker {

        private int syncResult = 0;
        private int result = 0;

        private synchronized void syncIncrement() {
            syncResult++;
        }

        private void increment() {
            result++;
        }

    }

    private static class Task1 implements Runnable {

        private Worker worker;
        private long startTime;
        private long endTime;

        private Task1(Worker worker) {
            this.worker = worker;
        }

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                worker.increment();
            }
            endTime = System.currentTimeMillis();
        }

        private long getTime() {
            return endTime - startTime;
        }

    }

    private static class Task2 implements Runnable {

        private Worker worker;
        private long startTime;
        private long endTime;

        private Task2(Worker worker) {
            this.worker = worker;
        }

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                worker.syncIncrement();
            }
            endTime = System.currentTimeMillis();
        }

        private long getTime() {
            return endTime - startTime;
        }

    }

}
