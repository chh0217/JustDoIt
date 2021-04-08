package ch.socketversion;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 云岩
 * @description
 * @date 2020/8/5 8:46 下午
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();


            long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                //向服务器端发送一条消息
                bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
                bw.flush();

                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("服务器：" + mess);
            }
            System.out.println("总时间：" + (System.currentTimeMillis() - start));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
