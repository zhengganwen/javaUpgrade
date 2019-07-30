package com.study.demo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Classname TestSemapfore
 * @Description 信号灯(模拟买票操作 ， 两个窗口进行卖票)
 * @Date 2019/7/30 15:11
 * @Created by Ms.zheng
 */
public class TestSemapfore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        //四个线程同时进行买票操作
        for (int i = 0; i < 4; i++) {

            new Thread(() -> {
                try {
                    //占领窗口
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始买票");
                    //买票操作
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "购票成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //完成操作必须释放窗口
                    semaphore.release();
                }

            }, String.valueOf(i)).start();

        }


    }

}
