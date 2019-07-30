package com.study.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

/**
 * @Classname TestCountDownLatch
 * @Description 倒计时（火箭发射）
 * @Date 2019/7/30 15:10
 * @Created by Ms.zheng
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(7);

        for (int i = 0; i < 7; i++) {

            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "检查正常");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //计数减一
                    //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
                    countDownLatch.countDown();
                }


            }, String.valueOf(i)).start();

        }
        try {
            countDownLatch.await();
            System.out.println("火箭发射");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
