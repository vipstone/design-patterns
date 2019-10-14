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

        // 定义一个中介者
        MeditorCompany meditorCompany = new MeditorCompanyBeiKe("贝克找房");
        // 定义一个卖家一个买家同事类
        ColleagueSeller colleagueSeller = new ColleagueSeller(meditorCompany);
        ColleagueBuyer colleagueBuyer = new ColleagueBuyer(meditorCompany);

        // 给中介公司注册买家、卖家
        meditorCompany.setBuyer(colleagueBuyer);
        meditorCompany.setSeller(colleagueSeller);

        // 操作
        colleagueSeller.send("卖家发布需求...");
        colleagueBuyer.send("买家发布需求...");
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

// 抽象的中介公司角色，中介者角色中一般都要包含各个同事角色，因为它要负责这些对象之间的交互
abstract class MeditorCompany {
    // 中介公司名称
    private String name;
    protected ColleagueSeller seller; // 卖家-同事角色
    protected ColleagueBuyer buyer; // 买家-同事角色
    // 发布一个需求，由中介公司去代为发布，入参为需求内容、发布人
    abstract void publish(String message, Colleaguer colleaguer);

    public MeditorCompany(String name) {
        this.name = name;
    }
    public ColleagueSeller getSeller() {
        return seller;
    }

    public void setSeller(ColleagueSeller seller) {
        this.seller = seller;
    }

    public ColleagueBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(ColleagueBuyer buyer) {
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
// 具体的中介公司-贝克找房
class MeditorCompanyBeiKe extends MeditorCompany{
    public MeditorCompanyBeiKe(String name) {
        super(name);
    }

    @Override
    void publish(String message, Colleaguer colleaguer) {
        if (colleaguer instanceof ColleagueSeller) { // 如果是卖家发布，则买家进行接收
            buyer.accept(message);
        } else if(colleaguer instanceof ColleagueBuyer) { // 如果是买家发布，则卖家进行接收
            seller.accept(message);
        }
    }
}
// 抽象的同事角色
abstract class Colleaguer {
    protected MeditorCompany meditorCompany; // 对同事类而言，中介者必须是可见的
    public Colleaguer(MeditorCompany meditorCompany) {
        this.meditorCompany = meditorCompany;
    }
}
// 卖家-同事角色
class ColleagueSeller extends Colleaguer {
    public ColleagueSeller(MeditorCompany meditorCompany) {
        super(meditorCompany);
    }
    // 同事类发布一个需求，不过是通过中介公司去发布，发布人是自己
    public void send(String message) {
        meditorCompany.publish(message, this);
    }
    public void accept(String message) {
        System.out.println("卖家接收到的消息是：" + message);
    }
}
// 买家-同事角色
class ColleagueBuyer extends Colleaguer {
    public ColleagueBuyer(MeditorCompany meditorCompany) {
        super(meditorCompany);
    }
    public void send(String message) {
        meditorCompany.publish(message, this);
    }
    public void accept(String message) {
        System.out.println("买家接收到的消息是：" + message);
    }
}
