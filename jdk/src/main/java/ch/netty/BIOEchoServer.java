package ch.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 云岩
 * @description
 * @date 2020/11/4 3:02 下午
 */
public class BIOEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8001);
        System.out.println("server start");
        while (true) {
            //开始接受客户端连接
            Socket socket = serverSocket.accept();

            System.out.println("one client conn: " + socket);
            // 启动线程处理连接数据
            new Thread(() -> {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = bufferedReader.readLine()) != null) {
                        System.out.println("receive msg: " + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
