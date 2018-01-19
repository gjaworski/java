package jaworskg.java.concurrency.e.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadExecutorMain {

    private static final int MAX_NUMBER_OF_ITERATIONS = 8;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < MAX_NUMBER_OF_ITERATIONS; i++) {
            service.submit(new Task(i));
        }
        System.out.println("Number of working threads: " + Thread.activeCount());
        service.shutdown();
    }

    private static class Task implements Runnable {

            private final int id;

        private Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10);
                System.out.println("Running thread: " + id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
