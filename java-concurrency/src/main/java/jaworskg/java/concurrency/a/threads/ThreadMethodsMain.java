package jaworskg.java.concurrency.a.threads;

import java.util.Arrays;

public class ThreadMethodsMain {

    public static void main(String[] args) {
        Thread current = Thread.currentThread();
        Thread custom = new Thread(() -> {
        }, "Worker");

        System.out.println("Current thread:");
        printThreadInfo(current);

        System.out.println();
        System.out.println("Custom thread:");
        printThreadInfo(custom);
    }

    private static void printThreadInfo(Thread thread) {
        System.out.println("Id: " + thread.getId());
        System.out.println("Name: " + thread.getName());
        System.out.println("Priority: " + thread.getPriority());
        System.out.println("State: " + thread.getState());
        System.out.println("Group: " + thread.getThreadGroup());
        System.out.println("Alive: " + thread.isAlive());
        System.out.println("Daemon: " + thread.isDaemon());
        System.out.println("Interrupted: " + thread.isInterrupted());
        printStackTrace(thread.getStackTrace());
    }

    private static void printStackTrace(StackTraceElement[] elements) {
        System.out.println("Stack Trace:");
        Arrays.stream(elements).forEach(element -> System.out.println("\t" + element));
    }

}
