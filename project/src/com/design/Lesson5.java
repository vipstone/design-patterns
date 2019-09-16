package com.design;

public class Lesson5 {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true

        Singleton2 s3 = Singleton2.getInstance();
        Singleton2 s4 = Singleton2.getInstance();
        System.out.println(s3 == s4); // true
    }
}

// 懒汉式（比较懒，不想一步到位）
class Singleton {
    // 使用类变量来缓存创建过的实例
    private static volatile Singleton instance = null; // 保证instance线程同步
    // 单例模式确保构造为私有
    private Singleton() {
    }
    // 使用synchronized关键字修饰，确保线程安全
    static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// 饿汉式（一步到位直接 new）
class Singleton2 {
    // 使用类变量来缓存创建过的实例
    private static final Singleton2 instance = new Singleton2();
    private Singleton2() {
    }
    static Singleton2 getInstance() {
        return instance;
    }
}
