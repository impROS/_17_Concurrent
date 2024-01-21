package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * ReentrantLock, Java'nın java.util.concurrent.locks paketinde bulunan bir kilit (lock) implementasyonudur.
 *  Bu kilit, diğer kilit türlerinden farklı olarak, bir iş parçacığının aynı kilit üzerinde birden fazla
 *  kez kilitleme (locking) ve serbest bırakma (unlocking) işlemi yapabilmesine olanak tanır.
 *  Bu özelliği nedeniyle "reentrant" (yeniden giriş) olarak adlandırılır.
 *
 */
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable withdrawRunnable1 = () -> {
            account.withdraw(300);
        };

        Runnable withdrawRunnable2 = () -> {
            account.withdraw(500);
        };

        Runnable withdrawRunnable3 = () -> {
            account.withdraw(400);
        };

        // İki iş parçacığını eşzamanlı olarak çalıştırmak için ExecutorService kullanılır.
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(withdrawRunnable1);
        executorService.submit(withdrawRunnable2);
        executorService.submit(withdrawRunnable3);

        executorService.shutdown();
    }
}