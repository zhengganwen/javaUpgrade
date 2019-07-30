package com.study.demo.singleton;

/**
 * @Classname Singleton1
 * @Description 饿汉模式，线程安全
 * @Date 2019/7/30 14:36
 * @Created by Ms.zheng
 */
public class Singleton1 {

    private static final Singleton1 singleton1 = new Singleton1();

    /**
     * 单例模式最重要的条件（私有的构造方法）
     */
    private Singleton1(){ }

    public  static Singleton1 getSingleton1(){
        return singleton1;
    }

}
