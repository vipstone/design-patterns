package com.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 */
public class Lesson21 {
    public static void main(String[] args) {
        // ------------- 1.观察者模式基础示例 Start -------------
        // 创建主题（发布者）
        ConcreteSubject subject = new ConcreteSubject();
        // 创建观察者（订阅者）
        Observer observer = new ConcreteObserver();
        // 关联订阅
        subject.attach(observer);
        // 改变主题（发布者）状态，发送通知
        subject.change();
        // ------------- 观察者模式基础示例 End -------------

        // ------------- 2.观察者模式实例 Start -------------
        // 创建图书平台（发布者）
        Platform platform = new Platform();
        // 创建读者 A（订阅者）
        Reader reader = new Reader("A");
        // 读者 A 订阅图书通知
        platform.attach(reader);
        // 创建读者 （订阅者）
        Reader reader2 = new Reader("B");
        // 读者 B 订阅图书通知
        platform.attach(reader2);
        platform.change("《Java面试全解析》");
        // ------------- 观察者模式实例 End -------------
    }
}

// ------------- 观察者模式实例 Start -------------

/**
 * 读者接口（订阅接口）
 */
interface IReader {
    public void update(String bookName);
}

/**
 * 读者类（订阅者）
 */
class Reader implements IReader {
    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(String bookName) {
        System.out.println(name + "-收到了图书：" + bookName);
    }
}

/**
 * 平台接口（发布方接口）
 */
interface IPlatform {
    public void attach(IReader reader);

    public void detach(IReader reader);

    public void notifyObservers(String bookName);
}

/**
 * 具体平台类（发布方）
 */
class Platform implements IPlatform {
    // 存放使用观察者（订阅者）
    private List<IReader> list = new ArrayList();

    @Override
    public void attach(IReader reader) {
        // 添加观察者（订阅者）
        list.add(reader);
    }

    @Override
    public void detach(IReader reader) {
        // 删除观察者（订阅者）
        list.remove(reader);
    }

    @Override
    public void notifyObservers(String bookName) {
        // 通知所有观察者（订阅者）
        for (IReader reader : list) {
            reader.update(bookName);
        }
    }

    /**
     * 通知方法
     */
    public void change(String bookName) {
        this.notifyObservers(bookName);
    }
}

// ------------- 观察者模式实例 End -------------

// ------------- 观察者模式基础示例 Start -------------

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
    // 存放观察者（订阅者）
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

// ------------- 观察者模式基础示例 End -------------

