package jaworskg.java.concurrency.c.wrappers;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain1 {

    private static final int NUMBER_OF_CASH_FLOWS = 100000;

    public static void main(String[] args) throws InterruptedException {
        final Bank bank = new Bank();
        final double cashFlow = 400;
        final Account account1 = new Account(1000);
        final Account account2 = new Account(3000);

        Thread t1 = new Thread(new Task1(bank, cashFlow, account1, account2));
        Thread t2 = new Thread(new Task1(bank, cashFlow, account2, account1));
        Thread t3 = new Thread(new Task2(bank, cashFlow, account1, account2));
        Thread t4 = new Thread(new Task2(bank, cashFlow, account2, account1));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(1000);

        System.out.println("Thread 1 state: " + t1.getState());
        System.out.println("Thread 2 state: " + t2.getState());
        System.out.println("Thread 3 state: " + t3.getState());
        System.out.println("Thread 4 state: " + t4.getState());

        System.exit(0);
    }

    private static class Account {

        private ReentrantLock lock = new ReentrantLock(false);
        private double balance;

        private Account(double balance) {
            this.balance = balance;
        }

        private void apply(double cashFlow) {
            balance = balance + cashFlow;
        }
    }

    private static class Bank {

        private void transferMoney(Account account1, Account account2, double cashFlow) {
            synchronized (account1) {
                synchronized (account2) {
                    account1.apply(cashFlow);
                    account2.apply(-cashFlow);
                }
            }
        }

        private void safeTransferMoney(Account account1, Account account2, double cashFlow) {
            if (account1.lock.tryLock()) {
                try {
                    if (account2.lock.tryLock()) {
                        try {
                            account1.apply(cashFlow);
                            account2.apply(-cashFlow);
                        } finally {
                            account2.lock.unlock();
                        }
                    }
                } finally {
                    account1.lock.unlock();
                }
            }
        }

    }

    private static class Task1 implements Runnable {

        private final Bank bank;
        private final double cashFlow;
        private final Account account1;
        private final Account account2;

        private Task1(Bank bank, double cashFlow, Account account1, Account account2) {
            this.bank = bank;
            this.cashFlow = cashFlow;
            this.account1 = account1;
            this.account2 = account2;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUMBER_OF_CASH_FLOWS; i++) {
                bank.transferMoney(account1, account2, cashFlow);
            }
        }

    }

    private static class Task2 implements Runnable {

        private final Bank bank;
        private final double cashFlow;
        private final Account account1;
        private final Account account2;

        private Task2(Bank bank, double cashFlow, Account account1, Account account2) {
            this.bank = bank;
            this.cashFlow = cashFlow;
            this.account1 = account1;
            this.account2 = account2;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUMBER_OF_CASH_FLOWS; i++) {
                bank.safeTransferMoney(account1, account2, cashFlow);
            }
        }

    }

}
