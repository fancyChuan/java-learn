package cn.fancychuan.app.timeserver.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8085;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("使用默认端口...");
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("timeserver is started in port: " + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("the time server close");
                server.close();
                server = null;
            }
        }
    }
}


/**
 * 具体处理socket请求
 */
class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("time server received order: " + body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new java.util.Date(System.currentTimeMillis()).toString()
                        : "BAD ORDER";
                out.println(currentTime);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}