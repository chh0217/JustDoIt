package ch;

/**
 * @author 云岩
 * @description
 * @date 2020/10/29 4:19 下午
 */
public class RunTest {

    public static void hahaha() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"   开始");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"   结束");

    }
}
