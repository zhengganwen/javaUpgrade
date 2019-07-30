package com.study.demo.singleton;

/**
 * @Classname Singleton2
 * @Description 双重锁模式,线程安全
 * @Date 2019/7/30 14:42
 * @Created by Ms.zheng
 */
public class Singleton2 {

    private static volatile Singleton2 singleton2 = null;

    private Singleton2() {
    }

    public static Singleton2 getSingleton2() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }


}
