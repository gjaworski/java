package jaworskg.java.concurrency.a.threads;

public class ThreadCreationMain {

    public static void main(String[] args) {
        runThread();
        runRunnable();
    }

    private static void runThread() {
        new ThreadExample().start();
    }

    private static void runRunnable() {
        new Thread(new RunnableExample()).start();
    }

    private static class ThreadExample extends Thread {

        @Override
        public void run() {
            new Task("Thread!").doWork();
        }

    }

    private static class RunnableExample implements Runnable {

        @Override
        public void run() {
            new Task("Runnable!").doWork();
        }

    }

    private static class Task {

        private final String message;

        Task(String message) {
            this.message = message;
        }

        void doWork() {
            for (int i = 0; i < 10; i++) {
                System.out.println(message);
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
