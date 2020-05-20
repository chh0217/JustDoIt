package ch.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author chenhang
 * @date 2020/3/24 上午8:02
 * @description
 */
public class BufferTest {

    public static void main(String[] args) {

    }

    /**
     * 翻转
     */
    public void flip(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // 将hello放入byteBuffer
        byteBuffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.toString());
//        System.out.println(byteBuffer.mark());

        //必须要转成字节
//        byteBuffer.put('H');

        byteBuffer.put(0, (byte) 'M').put((byte) 'w');
        System.out.println(byteBuffer.toString());

        // 这个时候直接get是无法拿出数据的
        // 如果想读出原先写入的数据，需要将limit设置为写入位置的末端，如下是手动写法
        // limit记录读取的上限位置
//        byteBuffer.limit(byteBuffer.position()).position(0);
//        System.out.println(byteBuffer.toString());
        // 这是api提供的写法
        // pos=0 lim=6 cap=10
//        System.out.println("flip " + byteBuffer.flip().toString());
        // pos=0 lim=10 cap=10  rewind不会变化limit
//        System.out.println("rewind " + byteBuffer.rewind().toString());
    }

    /**
     * 释放
     */
    public void release(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byte[] myByteArray = new byte[10];
        // 将hello放入byteBuffer
        byteBuffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(byteBuffer.toString());
        for (int i = 0; byteBuffer.hasRemaining( );i++) {
            myByteArray[i] = byteBuffer.get( );
        }

        // 和上面的一样
//        int count = byteBuffer.remaining( );
//        for (int i = 0; i < count;i++) {
//            myByteArray [i] = byteBuffer.get( );
//        }
    }

    /**
     * 压缩
     */
    public void compact(){

    }

    /**
     * 复制
     */
    public void copy(){
        CharBuffer buffer = CharBuffer.allocate (8);
        buffer.position (3).limit (6).mark( ).position (5);
        CharBuffer dupeBuffer = buffer.duplicate( );
        buffer.clear( );
    }
}
