package jaworskg.java.concurrency.a.threads;

public class ThreadInterruptionMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
        thread.interrupt();


    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted!");
            }
        }

    }

}
