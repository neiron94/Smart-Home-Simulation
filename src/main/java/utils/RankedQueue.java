package utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for dequeue which has priority. Can be used in tree set.
 * @param <T> the type of elements held in this deque
 */
public class RankedQueue<T> implements Comparable<RankedQueue<T>> {
    private final Deque<T> queue;
    private final Priority priority;

    /**
     * Creates ranked queue with given priority.
     * @param priority priority of new queue
     */
    public RankedQueue(Priority priority) {
        this.queue = new LinkedList<>();
        this.priority = priority;
    }

    /**
     * Pushes an element at the head of this queue.
     * @param element element to push
     */
    public void push(T element) {
        queue.push(element);
    } // Add to front

    /**
     * Inserts the specified element at the tail of this queue.
     * @param element element to add
     */
    public void add(T element) {
        queue.add(element);
    } // Add to tail

    /**
     * Adds all the elements in the specified collection at the end of this queue.
     * @param elements the elements to be inserted into this queue
     */
    public void addAll(List<? extends T> elements) {
        queue.addAll(elements);
    }

    /**
     * Retrieves and removes the head of this queue.
     * @return the first element of this queue, or null if this queue is empty
     */
    public T poll() {
        return queue.poll();
    } // Delete from front

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return the head of this queue, or null if this queue is empty
     */
    public T peek() {
        return queue.peek();
    }

    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Removes all the elements from this collection.
     * The collection will be empty after this method returns.
     */
    public void clear() {
        queue.clear();
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(RankedQueue<T> queue) {
        return queue.priority.getValue() - this.priority.getValue();
    }
}
