package ch.lock.normallock;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author 云岩
 * @description 模拟转账并发出问题的场景
 * @date 2020/8/6 4:04 下午
 */
@Slf4j
public class Transfer {
    public static int balance = 0;
    final static CountDownLatch latch = new CountDownLatch(10000);


    public static void transfer() {
        int count = 10000;
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = balance + 1;
                System.out.println(balance);
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