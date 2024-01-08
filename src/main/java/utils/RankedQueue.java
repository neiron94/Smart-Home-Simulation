package utils;

import java.util.Queue;
import java.util.LinkedList;

public class RankedQueue<T> implements Comparable<RankedQueue<T>> {
    private final Queue<T> queue;
    private final int priority;

    public RankedQueue(Priority priority) {
        this.queue = new LinkedList<>();
        this.priority = priority.getValue();
    }

    public void add(T elem) {
        queue.add(elem);
    }

    public T poll() {
        return queue.poll();
    }

    public T peek() {
        return queue.peek();
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(RankedQueue<T> queue) {
        return queue.priority - this.priority;
    }
}
