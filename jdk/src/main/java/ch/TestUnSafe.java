package ch;

import sun.misc.Unsafe;

public class TestUnSafe {
    // 获取unsafe实例
    static final Unsafe unsafe = Unsafe.getUnsafe();

    // 记录变量 state在类 TestUnSafe 中的偏移位
    static final long stateOffset;

    //变量
    private volatile long state = 0;

    static {
        try {
            //获取state 变量在类 TestUnSafe 中的偏移值
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.
                    getDeclaredField("state"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        //创建实例,并且设置state值为1
        TestUnSafe test = new TestUnSafe();
        //
        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);
    }
}
