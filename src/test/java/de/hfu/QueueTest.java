package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    int queueSize = 3;
    Queue queue = new Queue(queueSize);

    @Test
    public void enqueue() {
        for (int i = 0; i < queueSize; ++i) {
            queue.enqueue(i);
        }

        for (int i = 0; i < queueSize; ++i) {
            assertEquals(i, queue.dequeue());
        }
    }

    /*
    Test ungültige Queuelänge
     */
    @Test(expected = IllegalArgumentException.class)
    public void invalidLength() {
        Queue queue1 = new Queue(-1);
        Queue queue2 = new Queue(0);
    }

    /*
    Test leere Queue
     */
    @Test(expected = IllegalStateException.class)
    public void emptyQueue() {
        for (int i = 0; i < queueSize; ++i) {
            queue.enqueue(i);
        }
        for (int i = 0; i < queueSize + 2; ++i) {
            assertEquals(i, queue.dequeue());
        }
    }
}