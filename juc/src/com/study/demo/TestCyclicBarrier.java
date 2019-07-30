package com.study.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Classname TestCyclicBarrier
 * @Description 内存屏障（集齐七龙珠才可以召唤深孔）
 * @Date 2019/7/30 15:12
 * @Created by Ms.zheng
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤龙珠");
        });

        for (int i = 0; i<7 ; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println(Thread.currentThread().getName());
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }finally {
                      cyclicBarrier.reset();

                    }

                }
            },String.valueOf(i)).start();

        }
    }
}
