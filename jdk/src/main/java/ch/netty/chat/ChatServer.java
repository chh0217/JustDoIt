package ch.netty.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 云岩
 * @description
 * @date 2020/11/4 8:23 下午
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        //建立连接
        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 阻塞
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    ssc.configureBlocking(false);
                    SocketChannel socketChannel = ssc.accept();
                    System.out.println("accept new conn:" + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //加入群聊
                    ChatHolder.join(socketChannel);
                } else if (selectionKey.isReadable()) {
                    // 读事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                }
            }
        }
    }

    private static class ChatHolder {
        private static final Map<SocketChannel, String> USER_MAP = new ConcurrentHashMap<>();

        /**
         * 加入群聊
         *
         * @param socketChannel
         */
        public static void join(SocketChannel socketChannel) {
            // 有人加入就给他分配一个id
            String userId = "用户" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
            send(socketChannel, "您的id为：" + userId + "\n\r");

            for (SocketChannel channel : USER_MAP.keySet()) {
                send(channel, userId + " 加入了群聊" + "\n\r");
            }

            // 将当前用户加入到map中
            USER_MAP.put(socketChannel, userId);
        }

        /**
         * 退出群聊
         *
         * @param socketChannel
         */
        public static void quit(SocketChannel socketChannel) {
            String userId = USER_MAP.get(socketChannel);
            send(socketChannel, "您退出了群聊" + "\n\r");
            USER_MAP.remove(socketChannel);

            for (SocketChannel channel : USER_MAP.keySet()) {
                if (channel != socketChannel) {
                    send(channel, userId + " 退出了群聊" + "\n\r");
                }
            }
        }

        /**
         * 扩散说话的内容
         *
         * @param socketChannel
         * @param content
         */
        public static void propagate(SocketChannel socketChannel, String content) {
            String userId = USER_MAP.get(socketChannel);
            for (SocketChannel channel : USER_MAP.keySet()) {
                if (channel != socketChannel) {
                    send(channel, userId + ": " + content + "\n\r");
                }
            }
        }

        /**
         * 发送消息
         *
         * @param socketChannel
         * @param msg
         */
        private static void send(SocketChannel socketChannel, String msg) {
            try {
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put(msg.getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

