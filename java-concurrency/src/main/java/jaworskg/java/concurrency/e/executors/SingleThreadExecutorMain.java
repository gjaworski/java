package jaworskg.java.concurrency.e.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorMain {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new Task());
        service.shutdown();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Running thread!");
        }

    }

}
