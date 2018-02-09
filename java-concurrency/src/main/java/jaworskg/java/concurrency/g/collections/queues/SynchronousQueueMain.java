package jaworskg.java.concurrency.g.collections.queues;

import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueMain {

    private static final String[] STRINGS = {"Cat", "Dog", "Bat"};

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        new Thread(consumer).start();
        new Thread(producer).start();

    }

    private static class Producer implements Runnable {

        private final SynchronousQueue<String> queue;

        private Producer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Arrays.stream(STRINGS)
                    .forEach(str -> {
                        try {
                            queue.put(str);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Element: " + str + " has been produced");
                    });
        }

    }

    private static class Consumer implements Runnable {

        private final SynchronousQueue<String> queue;

        private Consumer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (String ignored : STRINGS) {
                    Thread.sleep(1000);
                    System.out.println("Consumed: " + queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
