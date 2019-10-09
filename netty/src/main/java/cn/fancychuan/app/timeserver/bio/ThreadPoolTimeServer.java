package cn.fancychuan.app.timeserver.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池的伪异步TimeServer
 */
public class ThreadPoolTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8085;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("the time server is started in port: " + port);
            // 创建IO任务线程池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                Socket socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.close();
                server = null;
            }
        }
    }
}

class TimeServerHandlerExecutePool {
    private ExecutorService executor;
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        System.out.println("初始化线程池，可用处理器个数： " + Runtime.getRuntime().availableProcessors());
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize,
                120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void execute(Runnable task) {
        executor.execute(task);
    }
}