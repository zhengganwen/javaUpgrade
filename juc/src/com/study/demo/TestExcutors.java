package com.study.demo;

import java.util.concurrent.*;

/**
 * @Classname TestExcutors
 * @Description 线程池相关知识点
 * @Date 2019/7/25 13:38
 * @Created by Ms.zheng
 */
public class TestExcutors {

    public static void main(String[] args) {


        /**
         *
         * 线程池参数
         * new  ThreadPoolExecutor(int corePoolSize,
         *                                int maximumPoolSize,
         *                                long keepAliveTime,
         *                                TimeUnit unit,
         *                                BlockingQueue<Runnable> workQueue,
         *                                ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler)
         */

        /**
         * ThreadPoolExecutor的重要参数
         *
         * corePoolSize：核心线程数
         * 核心线程会一直存活，及时没有任务需要执行
         * 当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
         * 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
         * queueCapacity：任务队列容量（阻塞队列）
         * 当核心线程数达到最大时，新任务会放在队列中排队等待执行
         * maxPoolSize：最大线程数
         * 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
         * 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
         * keepAliveTime：线程空闲时间
         * 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
         * 如果allowCoreThreadTimeout=true，则会直到线程数量=0
         * allowCoreThreadTimeout：允许核心线程超时
         * rejectedExecutionHandler：任务拒绝处理器
         * 两种情况会拒绝处理任务：
         * 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
         * 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
         * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
         * ThreadPoolExecutor类有几个内部实现类来处理这类情况：
         * AbortPolicy 丢弃任务，抛运行时异常
         * CallerRunsPolicy 执行任务
         * DiscardPolicy 忽视，什么都不会发生
         * DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
         * 实现RejectedExecutionHandler接口，可自定义处理器
         *
         * 二、ThreadPoolExecutor执行顺序：
         *      线程池按以下行为执行任务
         *
         *
         * 当线程数小于核心线程数时，创建线程。
         * 当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
         * 当线程数大于等于核心线程数，且任务队列已满
         * 若线程数小于最大线程数，创建线程
         * 若线程数等于最大线程数，抛出异常，拒绝任务
         */

        /**
         * 阻塞队列
         *接口   BlockingQueue<Runnable>()
         */
        //基于数组的先进先出队列，有边界
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        //基于链表的先进先出队列，无边界
        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>();
        //无缓冲的等待队列，无界
        SynchronousQueue<Runnable> objects1 = new SynchronousQueue<>();



        /**
         * 拒绝策略
         * 接口   RejectedExecutionHandler
         */
        //队列满了，丢任务抛异常
        RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        //队列满了丢任务不抛异常
        RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        //讲最早进入队列的删掉，之后尝试加入队列
        RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        //如果添加线程池失败，主线程自动执行
        RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();


        /**
         * ThreadPoolExecutor执行顺序：
         *      线程池按以下行为执行任务
         *
         *
         * 当线程数小于核心线程数时，创建线程。
         * 当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
         * 当线程数大于等于核心线程数，且任务队列已满
         * 若线程数小于最大线程数，创建线程
         * 若线程数等于最大线程数，抛出异常，拒绝任务
         */

        /**
         * 创建线程池
         */

        /**
         * 1.创建只有一个单线程的线程池
         *本质
         *   new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
         *   本质核心线程数和最大线程数都是1，基于链表的先进先出无界队列
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();


        /**
         * 2.创建一个无限线程的的的线程池
         *  new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>())
         *  本质核心线程数为0，无缓冲区的无界等待队列
         */

        ExecutorService executorService1 = Executors.newCachedThreadPool();


        /**
         *
         * 3.创建一个固定大小的线程池
         * new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         *
         * 本质：核心线程数核最大线程数相等，基于链表的无界阻塞对列
         *
         */

        ExecutorService executorService2 = Executors.newFixedThreadPool(3);


        /**
         * 4.创建一个可执行定时任务的线程池
         *
         *
         * new ScheduledThreadPoolExecutor(corePoolSize)
         * new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());
         *
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);






    }
}
