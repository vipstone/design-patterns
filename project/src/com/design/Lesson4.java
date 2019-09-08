package com.design;

public class Lesson4 {
    public static void main(String[] args) {
        Factory factory = new TCLFactory();
        Television television = factory.newTelevision();
        Refrigerator refrigerator = factory.newRefrigerator();

        television.doSomething(); // 我是TCL电视机，我可以看电视
        refrigerator.doSomething(); // 我是TCL冰箱，我可以洗衣服

        factory = new MeiDFactory();
        television = factory.newTelevision();
        refrigerator = factory.newRefrigerator();

        television.doSomething(); // 我是美的电视机，我可以看电视
        refrigerator.doSomething(); // 我是美的冰箱，我可以洗衣服
    }
}

// 抽象工厂定义
interface Factory {
    // 可以生产电视
    Television newTelevision();
    // 可以生产冰箱
    Refrigerator newRefrigerator();
}

// 电视机抽象接口（抽象产品）
interface Television {
    // 产品可以做什么？
    void doSomething();
}

// 冰箱抽象接口（抽象产品）
interface Refrigerator {
    // 产品可以做什么？
    void doSomething();
}

// TCL工厂
class TCLFactory implements Factory {
    // 生产TCL电视机
    @Override
    public Television newTelevision() {
        return new TCLTelevision();
    }

    // 生产TCL冰箱
    @Override
    public Refrigerator newRefrigerator() {
        return new TCLRefrigerator();
    }
}
// MeiD工厂
class MeiDFactory implements Factory {
    // 生产美的电视
    @Override
    public Television newTelevision() {
        return new MeiDTelevision();
    }
    // 生产美的冰箱
    @Override
    public Refrigerator newRefrigerator() {
        return new MeiDRefrigerator();
    }
}
// TCL电视（具体产品类）
class TCLTelevision implements Television {
    @Override
    public void doSomething() {
        System.out.println("我是TCL电视机，我可以看电视");
    }
}
// 美的电视（具体产品类）
class MeiDTelevision implements Television {
    @Override
    public void doSomething() {
        System.out.println("我是美的电视机，我可以看电视");
    }
}
// TCL冰箱（具体产品类）
class TCLRefrigerator implements Refrigerator {
    @Override
    public void doSomething() {
        System.out.println("我是TCL冰箱，我可以洗衣服");
    }
}
// 美的冰箱（具体产品类）
class MeiDRefrigerator implements Refrigerator {
    @Override
    public void doSomething() {
        System.out.println("我是美的冰箱，我可以洗衣服");
    }
}