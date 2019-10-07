package com.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 */
public class Lesson21 {
    public static void main(String[] args) {
        // 创建主题（发布者）
        ConcreteSubject subject = new ConcreteSubject();
        // 创建观察者（订阅者）
        Observer observer = new ConcreteObserver();
        // 关联订阅
        subject.attach(observer);
        // 改变主题（发布者）状态，发送通知
        subject.change();
    }
}

/**
 * 抽象主题（发布者接口）
 */
interface Subject {
    public void attach(Observer o);

    public void detach(Observer o);

    public void notifyObservers();
}

/**
 * 具体主题（发布者）
 */
class ConcreteSubject implements Subject {
    // 存放使用观察者（订阅者）
    private List<Observer> list = new ArrayList();

    @Override
    public void attach(Observer o) {
        // 添加观察者（订阅者）
        list.add(o);
    }

    @Override
    public void detach(Observer o) {
        // 删除观察者（订阅者）
        list.remove(o);
    }

    @Override
    public void notifyObservers() {
        // 通知所有观察者（订阅者）
        for (Observer o : list) {
            o.update();
        }
    }

    /**
     * 通知方法
     */
    public void change() {
        this.notifyObservers();
    }
}

/**
 * 抽象观察者（订阅者接口）
 */
interface Observer {
    public void update();
}

/**
 * 具体观察者（订阅者）
 */
class ConcreteObserver implements Observer {

    @Override
    public void update() {
        // 主题有更新之后，执行的具体订阅（通知）方法
        System.out.println("我收到了通知~");
    }
}