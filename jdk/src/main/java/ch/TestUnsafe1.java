package ch;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe1 {
    static final Unsafe unsafe;
    static final long stateOffset;
    private volatile long state = 0;

    static {
        try {
            //使用反射获取Unsafe的成员交量 the Unsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可存取
            field.setAccessible(true);
            //获取该变莹的值
            unsafe = (Unsafe) field.get(null);
            //获取state在TestUnSafe 中 的偏移量
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.
                    getDeclaredField("state"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        //创建实例,并且设置state值为1
        TestUnsafe1 test = new TestUnsafe1();
        //
        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);
    }
}
