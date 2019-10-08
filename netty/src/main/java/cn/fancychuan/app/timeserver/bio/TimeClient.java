package cn.fancychuan.app.timeserver.bio;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8085;

        try (
                Socket socket = new Socket("localhost", port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            out.println("QUERY TIME ORDER");
            out.flush();
            System.out.println("send order to server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
