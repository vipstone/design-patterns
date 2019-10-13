package com.design;

public class Lesson20 {
    public static void main(String[] args) {
        // 声明中介者
        Mediator mediator = new ConcreteMediator();
        // 定义两个同事角色
        Colleague1 c1 = new Colleague1(mediator);
        Colleague2 c2 = new Colleague2(mediator);

        // 给中介者设置同事角色
        mediator.setC1(c1);
        mediator.setC2(c2);

        c1.depMethod1();
    }
}

// 抽象同事类
abstract class Colleague {
    // 每个同事类角色都必须知道中介者角色的存在
    protected Mediator mediator;
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}

// 具体同事类
class Colleague1 extends Colleague {
    public Colleague1(Mediator mediator) {
        super(mediator);
    }

    // 自发行为
    public void doSelfMethod1() {
        System.out.println("11111");
    }

    // 依赖方法
    public void depMethod1() {
        // 委托给中介者处理的任务
        this.mediator.doSomething();
    }
}
// 具体同事类
class Colleague2 extends Colleague {
    public Colleague2(Mediator mediator) {
        super(mediator);
    }

    // 自发行为
    public void doSelfMethod2() {
        System.out.println("22222");
    }

    // 依赖方法
    public void depMethod2() {
        // 委托给中介者处理的任务
        this.mediator.doSomething();
    }
}


// 抽象中介者角色
abstract class Mediator {
    // 中介者要协调的同事角色
    protected Colleague1 c1;
    protected Colleague2 c2;

    // 中介者模式的业务逻辑抽象
    abstract void doSomething();

    public Colleague1 getC1() {
        return c1;
    }

    public void setC1(Colleague1 c1) {
        this.c1 = c1;
    }

    public Colleague2 getC2() {
        return c2;
    }

    public void setC2(Colleague2 c2) {
        this.c2 = c2;
    }
}

// 具体中介者
class ConcreteMediator extends Mediator{
    @Override
    void doSomething() {
        this.c1.doSelfMethod1();
        this.c2.doSelfMethod2();
    }
}
