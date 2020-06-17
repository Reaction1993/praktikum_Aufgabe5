package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void enqueue() {
        int queueSize = 3;
        Queue queue = new Queue(queueSize);
        for(int i=0;i<queueSize;++i){
            queue.enqueue(i);
        }

        for(int i=0;i<queueSize;++i){
            assertEquals(i, queue.dequeue());
        }
    }

    @Test
    public void dequeue() {
    }
}