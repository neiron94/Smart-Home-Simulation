package utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RankedQueue<T> implements Comparable<RankedQueue<T>> {
    private final Deque<T> queue;
    private final Priority priority;

    public RankedQueue(Priority priority) {
        this.queue = new LinkedList<>();
        this.priority = priority;
    }

    public void push(T elem) {
        queue.push(elem);
    } // Add to front

    public void add(T elem) {
        queue.add(elem);
    } // Add to tail

    public void addAll(List<? extends T> elems) {
        queue.addAll(elems);
    }

    public T poll() {
        return queue.poll();
    } // Delete from front

    public T peek() {
        return queue.peek();
    } // Look from front

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(RankedQueue<T> queue) {
        return queue.priority.getValue() - this.priority.getValue();
    }
}
