package com.design;

public class Lesson9 {
    public static void main(String[] args) {
        MobilePhoneAbstraction m1 = new HuaWeiMobilePhone(new ChatSoft());
        HuaWeiMobilePhone m2 = new HuaWeiMobilePhone(new GameSoft());

        m1.run();
        m2.run();

        m2.gpuTurborRun();
    }
}

// 手机抽象类
abstract class MobilePhoneAbstraction {
    protected SoftImplementor impl;
    public abstract void run();
    public MobilePhoneAbstraction(SoftImplementor impl) {
        this.impl = impl;
    }
}
// 手机软件的抽象类
abstract class SoftImplementor {
    public abstract void rawrun();
}
//手机品牌的实现类
class HuaWeiMobilePhone extends MobilePhoneAbstraction{
    public HuaWeiMobilePhone(SoftImplementor impl) {
        super(impl);
    }
    @Override
    public void run() {
        // 使用委托：调用HuaWeiMobilePhone的run时，实际调用的是SoftImplementor的rawrun
        this.impl.rawrun();
    }
    public void gpuTurborRun(){
        System.out.println("GPU Turbo Running start...");
        this.run();
        System.out.println("GPU Turbo Running end...");
    }
}
// 聊天软件——ChatSoft
class ChatSoft extends SoftImplementor{
    @Override
    public void rawrun() {
        System.out.println("ChatSoft rawrun...");
    }
}
// 游戏软件——GameSoft
class GameSoft extends SoftImplementor {
    @Override
    public void rawrun() {
        System.out.println("GameSoft rawrun...");
    }
}