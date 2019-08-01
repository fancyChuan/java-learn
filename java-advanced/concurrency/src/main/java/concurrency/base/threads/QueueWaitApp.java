package concurrency.base.threads;

import java.util.LinkedList;
import java.util.Queue;

public class QueueWaitApp {
    int MAX_SIZE;
    Queue<Integer> queue;

    public QueueWaitApp() {
        this.MAX_SIZE = 10;
        this.queue = new LinkedList<Integer>();
    }


}
