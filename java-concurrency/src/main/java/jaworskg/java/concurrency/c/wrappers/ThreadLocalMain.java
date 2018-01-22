package jaworskg.java.concurrency.c.wrappers;

public class ThreadLocalMain {

    private static ThreadLocal<String> localName = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Task(), "Task1").start();
        new Thread(new Task(), "Task2").start();
    }

    private static synchronized void setThreadName(String threadName) {
        localName.set(threadName);
    }

    private static synchronized void displayThreadName() {
        System.out.println(localName.get());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            setThreadName(Thread.currentThread().getName());
            displayThreadName();
        }

    }

}
