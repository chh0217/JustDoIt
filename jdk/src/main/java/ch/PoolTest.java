package ch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 云岩
 * @description
 * @date 2020/10/29 4:16 下午
 */
@Slf4j
public class PoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor allExecutor = new ThreadPoolExecutor(1, 1, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(1), new ThreadFactoryBuilder().setNameFormat("retryClient-pool-%d").build()
                , (r, executor) ->
        {
            log.info(Thread.currentThread().getName() + " 主线程开始执行任务");
            r.run();
            log.info(Thread.currentThread().getName() + " 主线程执行任务结束");
        });


        for (; ; ) {
            allExecutor.execute(() -> {
                try {
                    RunTest.hahaha();
                } catch (InterruptedException e) {
                    log.error(" 出错了 =============== ");
                }
            });
        }
    }
}
