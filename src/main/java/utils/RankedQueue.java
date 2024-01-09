package utils;

import java.util.Deque;
import java.util.LinkedList;

public class RankedQueue<T> implements Comparable<RankedQueue<T>> {
    private final Deque<T> queue;
    private final int priority;

    public RankedQueue(Priority priority) {
        this.queue = new LinkedList<>();
        this.priority = priority.getValue();
    }

    public void push(T elem) {
        queue.push(elem);
    } // Add to front

    public void add(T elem) {
        queue.add(elem);
    } // Add to tail

    public T poll() {
        return queue.poll();
    } // Delete from front

    public T peek() {
        return queue.peek();
    } // Look from front

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(RankedQueue<T> queue) {
        return queue.priority - this.priority;
    }
}
