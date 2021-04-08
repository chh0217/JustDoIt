package ch.lock.normallock;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 云岩
 * @description
 * @date 2020/8/6 3:55 下午
 */
@Slf4j
public class LockTransfer {
    public static int balance = 0;
    private static Lock lock = new ReentrantLock();
    final static CountDownLatch latch = new CountDownLatch(10000);


    public static void transfer() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    balance = balance + 1;
                    System.out.println(balance);
                } finally {
                    lock.unlock();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
    }


    public static void main(String[] args) {
        transfer();
        System.out.println("balance:" + balance);
    }
}