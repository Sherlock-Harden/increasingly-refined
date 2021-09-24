package org.practise.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/18 14:13
 **/
public class SocketServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(15001);
    while (true) {
      System.out.println("等待客户端连接。");
      Socket clientSocket = serverSocket.accept();
      System.out.println("客户端连接了。");
      handler(clientSocket);
    }
  }

  private static void handler(Socket socket) throws IOException {
    byte[] msg = new byte[1024];
    int read = socket.getInputStream().read(msg);
    if (read != -1) {
      System.out.println("接收到客户端数据：" + new String(msg, 0, read));
    }
    socket.getOutputStream().write("Hello client".getBytes());
    socket.getOutputStream().flush();
  }
}
