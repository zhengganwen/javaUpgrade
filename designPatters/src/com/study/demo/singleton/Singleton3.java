package com.study.demo.singleton;

/**
 * @Classname Singleton3
 * @Description 静态内部类实现单例模式,线程安全
 * @Date 2019/7/30 14:49
 * @Created by Ms.zheng
 */
public class Singleton3 {

     private static class TestSing{
         private static final Singleton3 singleton3 = new Singleton3();
    }

    private Singleton3(){}

    public static Singleton3 getSingleyon3(){

         return TestSing.singleton3;
    }
}
