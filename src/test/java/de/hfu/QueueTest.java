package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void enqueue() {
        Queue queue = new Queue(3);
        for(int i=0;i<3;++i){
            queue.enqueue(i);
        }

        for(int i=0;i<3;++i){
            assertEquals(i, queue.dequeue());
        }
    }

    @Test
    public void dequeue() {
    }
}