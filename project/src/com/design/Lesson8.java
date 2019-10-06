package com.design;

public class Lesson8 {
    public static void main(String[] args) {
        // 通过Adapter继承Adaptee实现了Adaptee角色的调用
        Target target = new Adapter();
        target.targetMethod1();
        target.targetMethod2();
    }
}
/*************************** 类适配器 ****************************/

//要适配的目标接口
//interface Target {
//    public abstract void targetMethod1();
//    public abstract void targetMethod2();
//}
//
//// 要被适配的“接口”，即 Adaptee
//class Adaptee {
//    public void methodA() {
//        System.out.println("Adaptee methodA invoked.");
//    }
//    public void methodB() {
//        System.out.println("Adaptee methodB invoked.");
//    }
//}
//
//// 我们的适配器
//class Adapter extends Adaptee implements Target{
//    @Override
//    public void targetMethod1() {
//        System.out.println("Adapter targetMethod1 inkoked.");
//        methodA();
//    }
//    @Override
//    public void targetMethod2() {
//        System.out.println("Adapter targetMethod2 inkoked.");
//        methodB();
//    }
//}
/*************************** 类适配器 ****************************/

/*************************** 对象适配器 ****************************/

// 要适配的目标类,这里不是接口了注意
abstract class Target {
    public abstract void targetMethod1();
    public abstract void targetMethod2();
}
class Adaptee {
    public void methodA() {
        System.out.println("Adaptee methodA invoked.");
    }
    public void methodB() {
        System.out.println("Adaptee methodB invoked.");
    }
}
// 修改后的Adapter适配器
class Adapter extends Target{
    private Adaptee adaptee;
    public Adapter() {
        this.adaptee = new Adaptee();
    }
    @Override
    public void targetMethod1() {
        System.out.println("Adapter targetMethod1 inkoked.");
        adaptee.methodA();
    }
    @Override
    public void targetMethod2() {
        System.out.println("Adapter targetMethod2 inkoked.");
        adaptee.methodB();
    }
}
/*************************** 对象适配器 ****************************/