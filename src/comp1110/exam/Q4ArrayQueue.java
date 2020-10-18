package comp1110.exam;

import java.util.Arrays;

/**
 * COMP1110/6710 Exam, Question 4
 * <p>
 * This class represents a queue, in which elements are added and removed in a
 * first in, first out (FIFO) order. Duplicate elements are permitted.
 * When a queue is first created, it contains no elements.
 * The queue can grow to fit new elements as required.
 * Attempting to access an element of an empty queue throws an EmptyQueueException,
 * and does not result in any modification to the queue.
 * The Queue is implemented using an array data structure (a regular Java array),
 * and does not use any of the Java Collection classes.
 */
public class Q4ArrayQueue<T> {
    /**
     * An exception that is thrown when trying to dequeue or peek at an
     * empty queue. Do not modify this class.
     */
    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() { }
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        // FIXME complete this method
        return false;
    }

    /**
     * Add the given value to the back of the queue.
     *
     * @param value the value to add to the queue
     */
    public void enqueue(T value) {
        // FIXME complete this method
    }

    /**
     * Remove the value from the front of the queue and return it.
     *
     * @return the value that was taken off of the queue
     * @throws EmptyQueueException if the queue is currently empty
     */
    public T dequeue() throws EmptyQueueException {
        // FIXME complete this method
        return null;
    }

    /**
     * Get the value that is currently at the front of the queue,
     * but do not remove it from the queue.
     *
     * @return the value at the front of the queue
     * @throws EmptyQueueException if the queue is currently empty
     */
    public T first() throws EmptyQueueException {
        // FIXME complete this method
        return null;
    }

    /**
     * Check whether a given value is contained in this queue.
     * Specifically, returns true if value is not null and
     * an element e is contained in the queue such that e.equals(value).
     *
     * @param value the value to search for
     * @return true if the value is contained in this queue
     */
    public boolean contains(T value) {
        // FIXME complete this method
        return false;
    }

    /**
     * Create a String representation of this queue.
     * Elements on the queue are listed in order from front to back,
     * separated by commas (without spaces).
     * If the queue is empty, an empty string is returned.
     * For example, a queue containing the elements (from front to back)
     * "a", "b", and "c" would be represented as "a,b,c".
     *
     * @return a String representation of this queue
     */
    public String toString() {
        // FIXME complete this method
        return null;
    }
}
