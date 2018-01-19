package jaworskg.java.concurrency.e.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ExecutorMethodsMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> runnableResult = service.submit(new RunnableTask());
        System.out.println("Result from runnable task: " + runnableResult.get());

        Future<String> callableResult = service.submit(new CallableTask());
        System.out.println("Result from callable task: " + callableResult.get());
        System.out.println();
        service.shutdown();

        service = Executors.newSingleThreadExecutor();
        service.submit(new RunnableTask());
        System.out.println("After creation isTerminated() = " + service.isTerminated());
        System.out.println("After creation isShutdown() = " + service.isShutdown());
        service.shutdown();
        System.out.println("After shut down isTerminated() = " + service.isTerminated());
        System.out.println("After shut down isShutdown() = " + service.isShutdown());
        service.awaitTermination(1, SECONDS);
        System.out.println("After last task is done isTerminated() = " + service.isTerminated());
        System.out.println("After last task is done isShutdown() = " + service.isShutdown());
        System.out.println();

        service = Executors.newSingleThreadExecutor();
        service.submit(new RunnableTask());
        System.out.println("After creation isTerminated() = " + service.isTerminated());
        System.out.println("After creation isShutdown() = " + service.isShutdown());
        service.shutdownNow();
        service.awaitTermination(1, SECONDS);
        System.out.println("After shut down now isTerminated() = " + service.isTerminated());
        System.out.println("After shut down now isShutdown() = " + service.isShutdown());
        System.out.println();

        service = Executors.newSingleThreadExecutor();
        System.out.println("Invoking all threads");
        List<Future<Integer>> results = service.invokeAll(Arrays.asList(new Task(1), new Task(2), new Task(3)));
        service.shutdown();
        service.awaitTermination(1, SECONDS);
        System.out.println();


        service = Executors.newSingleThreadExecutor();
        System.out.println("Invoking any thread");
        Integer result = service.invokeAny(Arrays.asList(new Task(1), new Task(2), new Task(3)));
        System.out.println("Result is " + result);
        service.shutdown();
    }

    private static class RunnableTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted!");
            }
        }

    }

    private static class CallableTask implements Callable<String> {

        @Override
        public String call() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result";
        }

    }

    private static class Task implements Callable<Integer> {

        private int id;

        private Task(int id) {
            this.id = id;
        }

        @Override
        public Integer call() {
            System.out.println("Running thread: " + id);
            return id;
        }

    }

}
