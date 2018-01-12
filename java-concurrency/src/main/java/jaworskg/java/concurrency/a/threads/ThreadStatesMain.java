package jaworskg.java.concurrency.a.threads;

public class ThreadStatesMain {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread = new Thread(task);
        System.out.println("1. Thread state after creation: " + thread.getState());

        thread.start();
        System.out.println("2. Thread state after start(): " + thread.getState());

        runBlockingThread();
        task.finish();
        System.out.println("3. Thread state when blocked: " + thread.getState());

        Thread.sleep(2500);
        System.out.println("4. Thread state after wait(): " + thread.getState());

        task.doNotify();
        Thread.sleep(500);
        System.out.println("5. Thread state after wait(1000): " + thread.getState());

        task.doNotify();
        Thread.sleep(500);
        System.out.println("6. Thread state after work: " + thread.getState());

    }

    private static void runBlockingThread() {
        new Thread(() -> {
            try {
                work(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static synchronized void work(long millis) throws InterruptedException {
        System.out.println("\tworking!");
        Thread.sleep(millis);
    }

    private static class Task implements Runnable {

        private volatile boolean run = true;

        @Override
        public void run() {
            while(run) {}
            try {
                work(1);
                doWait();
                doWait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void finish() throws InterruptedException {
            Thread.sleep(100);
            run = false;
            Thread.sleep(100);

        }

        private synchronized void doWait() throws InterruptedException {
            wait();
        }

        private synchronized void doWait(long millis) throws InterruptedException {
            wait(millis);
        }

        private synchronized void doNotify() throws InterruptedException {
            Thread.sleep(100);
            notify();
            Thread.sleep(100);
        }

    }

}
