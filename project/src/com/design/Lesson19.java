package com.design;

import java.util.Vector;

/**
 * 迭代器模式
 */
public class Lesson19 {
    /**
     * 5.迭代器调用
     * @param args args
     */
    public static void main(String[] args) {
        // 定义聚族对象
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("Java");
        aggregate.add("MySQL");
        aggregate.add("Spring");
        // 遍历
        Iterator iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

/**
 * 1.抽象迭代器
 */
interface Iterator {
    public Object next();

    public boolean hasNext();
}

/**
 * 2.具体迭代器
 */
class ConcreteIterator implements Iterator {

    private ConcreteAggregate aggregate;
    private int index;
    private int size;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        this.index = 0;
        this.size = aggregate.size();
    }

    // 是否有下一个元素
    @Override
    public boolean hasNext() {
        return index < size;
    }

    // 返回下一个元素
    @Override
    public Object next() {
        if (index < size) {
            return aggregate.getElement(index++);
        }
        return null;
    }
}

/**
 * 3.抽象聚集
 */
interface Aggregate {
    public void add(Object obj);

    public Iterator createIterator();
}

/**
 * 4.具体聚集
 */
class ConcreteAggregate implements Aggregate {
    // 私有存储容器
    private Vector vector = new Vector();

    // 添加元素
    @Override
    public void add(Object obj) {
        this.vector.add(obj);
    }

    // 根据下标获取元素
    public Object getElement(int index) {
        return vector.get(index);
    }

    // 获取集合长度
    public int size() {
        return vector.size();
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
}
