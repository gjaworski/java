package jaworskg.java.concurrency.f.callables;

import java.util.concurrent.*;

public class CallableMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(new Task());
        System.out.println(result.get());
        executorService.shutdown();
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            return "Result from task thread";
        }

    }

}
