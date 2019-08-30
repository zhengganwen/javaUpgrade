package com.study.demo;

/**
 * @Classname TestCas
 * @Description TODO
 * @Date 2019/8/30 11:10
 * @Created by Ms.zheng
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CAS  compare and set
 *
 *  一.在高并发的情况下操作i++是有问题的
 *      解决方案一：在addRate方法上加锁，使线程同步，但是锁粒度太大
 *      解决方法二: 引入juc包下的AtomicInteger
 *
 *     }
 *
 *  */
public class TestCas {
    public static  volatile  int  rate=0;
    public static  volatile AtomicInteger atomicInteger =new AtomicInteger(0);

    public static  void addRate(){
        rate++;
    }

    public static synchronized void addRate1(){
        rate++;
    }


    /**
     * 自旋锁  ------ 比较并替换
     * public final int getAndAddInt(Object var1, long var2, int var4) {
     *         int var5;
     *         do {
     *             var5 = this.getIntVolatile(var1, var2);
     *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
     *
     *         return var5;
     *     }
     */

    public static synchronized void addRate2(){
        atomicInteger.getAndIncrement();
    }




    public static void main(String[] args) {

        for (int i = 1; i <=100 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    //   addRate();
                    //   addRate1();
                         addRate2();
                }

            }, String.valueOf(i)).start();

        }

        try {
            Thread.sleep(2000);
            //    System.out.println(rate);
            System.out.println(atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
