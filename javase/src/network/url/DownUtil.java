package network.url;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 多线程下载工具类
 *
 * todo:
 *  1. RandomAccessFile 是否线程安全？
 *  2. 为什么这里要用RandomAccessFile？
 */
public class DownUtil {
    private String path; // 下载资源的路径
    private String targetFile; // 所下载文件的保存位置
    private int threadNum; // 使用多少线程
    private DownThread[] threads; // 定义下载的线程对象
    private int fileSize; // 需要下载的文件总大小

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        this.threads = new DownThread[threadNum]; // 初始化多线程数组
    }

    public void download() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/git, image/jpeg, image/pjpeg, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");

        fileSize = conn.getContentLength();
        conn.disconnect();

        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        file.setLength(fileSize); // 设置本地文件大小
        file.close();

        // 开启多线程下载
        for (int i = 0; i < threadNum; i++) {
            int startPos = i * currentPartSize; // 每个线程下载的开始位置
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw"); // 每个线程使用一个RandomAccessFile进行下载
            currentPart.seek(startPos); // 定位该线程的下载位置
            threads[i] = new DownThread(startPos, currentPartSize, currentPart); // 创建下载线程
            threads[i].start(); // 启动
        }
    }

    public double getCompleteRate() {
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        return sumSize * 1.0 / fileSize; // 返回已经完成的百分比
    }


    /**
     * 内部私有类
     */
    private class DownThread extends Thread {
        private int startPos; // 当前线程的下载位置
        private int currentPartSize; // 当前线程负责下载的文件大小
        private RandomAccessFile currentPart; // 当前线程需要下载的文件块
        private int length; // 该线程已下载的字节数

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "image/git, image/jpeg, image/pjpeg, */*");
                conn.setRequestProperty("Accept-Language", "zh-CN");
                conn.setRequestProperty("Charset", "UTF-8");

                InputStream inputStream = conn.getInputStream();
                inputStream.skip(this.startPos); // 跳过startPos个字节，表明该线程只下载自己负责的那部分文件
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                while (length < currentPartSize && (hasRead = inputStream.read(buffer)) != -1) {
                    currentPart.write(buffer, 0 , hasRead);
                    length += hasRead; // 累积该线程下载的总大小
                }
                currentPart.close();
                inputStream.close();

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}
