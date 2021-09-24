package org.practise.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/09/18 14:59
 **/
public class SocketClient {

  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 15001);
    // 向服务端发送数据
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();

    socket.getOutputStream().write(line.getBytes());
    socket.getOutputStream().flush();

    byte[] msg = new byte[1024];
    int read = socket.getInputStream().read(msg);
    if (read != -1) {
      System.out.println("接收到服务端数据：" + new String(msg, 0, read));
    }
    socket.close();
  }
}
