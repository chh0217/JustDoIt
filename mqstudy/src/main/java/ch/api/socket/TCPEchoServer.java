package ch.api.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author 云岩
 * @description
 * @date 2020/8/5 5:28 下午
 */
public class TCPEchoServer {
    //SizE of receive buffer
    private static final int BUFSIZE = 32;

    public static void main(String[] args) throws IOException {
        // Test for correct # of args 10
        if (args.length != 1) {
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }
        int servPort = Integer.parseInt(args[0]);
        // Create a server socket to accept client connection requests
        ServerSocket servSock = new ServerSocket(servPort);
        // Size of received message
        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE]; // Receive
        while (true) { // Run forever, accepting and servicing connections
            Socket clntSock = servSock.accept(); // Get client connection
            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            System.out.println("Handling client at " + clientAddress);
            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();

            // Receive until client closes connection, indicated by -1 return
            while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                out.write(receiveBuf, 0, recvMsgSize);
            }
            clntSock.close(); // Close the socket. We are done with this client !
        }
    }
}
