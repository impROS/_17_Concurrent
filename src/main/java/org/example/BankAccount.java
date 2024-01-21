package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final Lock lock = new ReentrantLock();
    private int balance = 1000;

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (amount > 0 && amount <= balance) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                balance -= amount;
                System.out.println("Withdrawal successful. Remaining balance: " + balance);
            } else {
                System.out.println("Withdrawal failed. Insufficient funds.");
            }
        } finally {
            lock.unlock();
        }
    }

    //TryLock
    public void performTask() {
        if (lock.tryLock()) { // Kilit alınmaya çalışılır
            try {
                // Kilitli bölgede yapılacak işlemler
                System.out.println("Task performed inside the locked region.");
            } finally {
                lock.unlock(); // Kilit serbest bırakılır
            }
        } else {
            // Kilit alınamazsa yapılacak işlemler
            System.out.println("Could not acquire the lock.");
        }
    }
}
