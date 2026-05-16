package com.serain.exam.jingdong;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.jingdong
 * @Author: Serain
 * @CreateTime: 2026-05-14  13:33
 * @Description: TODO
 * @Version: 1.0
 */
public class demo {

    private final static ReentrantLock lock=new ReentrantLock();
    public static int num=0;
    public static void main(String[] args) {
        Thread t1=new Thread(new EvenPrinter());
        Thread t2=new Thread(new OddPrinter());
        t1.start();
        t2.start();
    }

    static class EvenPrinter implements Runnable{
        @Override
        public void run() {
            while (num <= 10) {
                synchronized (lock){
                    while (num % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Even"+num);
                    num++;
                    lock.notifyAll();
                }
            }
        }
    }
    static class OddPrinter implements Runnable{
        @Override
        public void run() {
            while (num <= 10) {
                synchronized (lock){
                    while (num % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Odd"+num);
                    num++;
                    lock.notifyAll();
                }
            }
        }
    }
}
