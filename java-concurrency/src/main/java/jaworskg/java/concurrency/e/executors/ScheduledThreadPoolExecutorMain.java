package jaworskg.java.concurrency.e.executors;

import java.time.LocalTime;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ScheduledThreadPoolExecutorMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Start of main: " + LocalTime.now());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> runnableResult = service.schedule(new RunnableTask(), 2, SECONDS);
        ScheduledFuture<?> callableResult = service.schedule(new CallableTask(), 4, SECONDS);
        System.out.println("Runnable result: " + runnableResult.get());
        System.out.println("Callable result: " + callableResult.get());
        service.shutdown();
        service.awaitTermination(10, SECONDS);

        System.out.println();
        System.out.println("Second part of main: " + LocalTime.now());
        service = Executors.newScheduledThreadPool(5);
        service.scheduleAtFixedRate(new FixedRateTask(), 5, 2, SECONDS);
        service.scheduleWithFixedDelay(new FixedDelayTask(), 5, 2, SECONDS);
        Thread.sleep(15000);
        service.shutdown();
    }

    private static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Start of run: " + LocalTime.now());
            System.out.println("Runnable thread working");
        }

    }

    private static class CallableTask implements Callable<String> {

        @Override
        public String call() {
            System.out.println("Start of call: " + LocalTime.now());
            System.out.println("Callable thread working");
            return "callableResult";
        }

    }

    private static class FixedRateTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Start of fix rate task: " + LocalTime.now());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fixed rate thread working");
        }

    }

    private static class FixedDelayTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Start of fix delay task: " + LocalTime.now());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fixed delay thread working");
        }

    }

}
