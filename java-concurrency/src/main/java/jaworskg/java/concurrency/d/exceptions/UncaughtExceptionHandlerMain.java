package jaworskg.java.concurrency.d.exceptions;

public class UncaughtExceptionHandlerMain {

    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "xxx");
        t.setUncaughtExceptionHandler(new TaskExceptionHandler());
        t.start();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            throw new RuntimeException("Exception thrown by thread!!!");
        }

    }

    private static class TaskExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Handling exception " + e.getClass() + " in thread " + t.getName());
            System.out.println("Current thread: " + Thread.currentThread().getName());
        }
    }

}
