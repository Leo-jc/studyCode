package com.serain.study.os;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private final int MAX_SIZE = 5;
    private final Queue<Integer> queue = new LinkedList<>();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == MAX_SIZE) {
                    wait();
                }
                System.out.println("Producer produced-" + value);
                queue.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    private void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.size() == 0) {
                    wait();
                }
                int value = queue.remove();
                System.out.println("Consumer consumed-" + value);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
