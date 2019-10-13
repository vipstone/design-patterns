package com.design;

/**
 * 模板模式
 */
public class Lesson25 {
    public static void main(String[] args) {
        // 模板模式基础使用
        AbstractTemplate tp = new ConcreteTemplateA();
        // 调用子类 A 的模板方法
        tp.templateMethod();
        tp = new ConcreteTemplateB();
        // 调用子类 B 的模板方法
        tp.templateMethod();
        //----------- 模板方法-上班实例 -----------
        AbstractWork work = new DriveToWork();
        // 开车上班
        work.gotoWork();
        work = new BusToWork();
        // 坐公交上班
        work.gotoWork();
    }
}

/**
 * 上班抽象（模板）类
 */
abstract class AbstractWork {
    // 模板方法
    public void gotoWork() {
        getup();
        commute();
        arrive();
    }

    // 起床洗漱
    public void getup() {
        System.out.println("1.起床洗漱");
    }

    // 通勤
    abstract void commute();

    // 到达公司
    public void arrive() {
        System.out.println("3.到达公司");
    }
}

/**
 * 开车上班
 */
class DriveToWork extends AbstractWork {

    @Override
    void commute() {
        System.out.println("2.开车去公司");
    }
}

/**
 * 坐公交上班
 */
class BusToWork extends AbstractWork {

    @Override
    void commute() {
        System.out.println("2.坐公交去公司");
    }
}

//---------------- 分割线 -----------------

/**
 * 抽象模板
 */
abstract class AbstractTemplate {
    // 模板方法
    public void templateMethod() {
        //（所有）基本方法
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    // 抽象方法，必须有子类实现
    protected abstract void abstractMethod();

    // 钩子方法，子类可以选择重写或不重写
    protected void hookMethod() {
    }

    // 具体方法，子类不可修改
    private final void concreteMethod() {
        System.out.println("抽象类中的具体方法");
    }
}

/**
 * 具体模板 A 类
 */
class ConcreteTemplateA extends AbstractTemplate {

    @Override
    protected void abstractMethod() {
        System.out.println("A 子类中重写的抽象方法");
    }
}

/**
 * 具体模板 B 类
 */
class ConcreteTemplateB extends AbstractTemplate {

    @Override
    protected void abstractMethod() {
        System.out.println("B 子类中重写的抽象方法");
    }
}