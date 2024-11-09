package companies.oracle.jdk;

import java.util.Queue;

public class BlockingQueue<E> {
    // implement code here ...
    /**
     * @param queue The underlying "wrapped" queue.
     */
    int maxSize;
    Queue<E> queue;
    public BlockingQueue(Queue<E> queue) {
        // implement code here ...
        this.queue = queue;
        this.maxSize = 10;
    }
    /**
     * Inserts the specified element into the underlying queue, waiting if
     * necessary for the underlying queue to be ready to accept new elements.
     * @param e the element to insert.
     */
    public void push(E e) {
        // implement code here ...
        synchronized (queue) {
            while (queue.size() == maxSize) {
                System.out.println("Queue is Full Wating");
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            queue.add(e);
            queue.notify();
        }
    }

    /**
     * Retrieves and removes the head of the underlying queue, waiting if
     * necessary until it is capable of providing an element.
     * @return the retrieved element
     */
    public E pull() {
        // implement code here ...
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Queue is Empty Wating");
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            E e = queue.poll();
            queue.notify();
            return e;
        }
    }
}