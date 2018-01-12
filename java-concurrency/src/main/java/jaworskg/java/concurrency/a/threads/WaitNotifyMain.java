package jaworskg.java.concurrency.a.threads;

public class WaitNotifyMain {

    public static void main(String[] args) {
        ConditionLoop conditionLoop = new ConditionLoop();
        runWaitingThread(conditionLoop);
        runNotifyingThread(conditionLoop);
    }

    private static void runWaitingThread(ConditionLoop conditionLoop) {
        new Thread(() -> {
            System.out.println("Running waiting thread!");
            try {
                conditionLoop.waitForCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void runNotifyingThread(ConditionLoop conditionLoop) {
        new Thread(() -> {
            System.out.println("Running notifying thread!");
            try {
                Thread.sleep(8000);
                conditionLoop.satisfyCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class ConditionLoop {

        private boolean condition = false;

        synchronized void waitForCondition() throws InterruptedException {
            while(!condition) {
                System.out.println("waiting!");
                wait();
            }
            System.out.println("waiting is over!");
        }

        synchronized void satisfyCondition() {
            condition = true;
            notify();
        }
    }
}
