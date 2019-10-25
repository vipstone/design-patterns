package com.design;

import sun.net.idn.Punycode;

// 状态模式
public class Lesson22 {

    public static void main(String[] args) {
//        ITelevision tv = new Telev(TVStateEnum.POWER_OFF_STATE);
//        tv.play(); // 如果直接进行播放的话，因为电视机处于待机状态，所以没有任何输出
//
//        // 必须先开机，才能播放
//        tv.powerOn();
//        tv.play();
//        tv.standby();
//        tv.powerOff();

//        Ctx context = new Ctx();
//        context.setCurrentState(new ConcreteState1());
//        context.request1();
//        context.request2();

        RemoteControlMachine context = new RemoteControlMachine();
        context.setCurrentState(new PowerOffState());
        context.play(); // 如果直接进行播放的话，因为电视机处于待机状态，所以没有任何输出

        context.powerOn();
        context.play();
        context.standby();
        context.powerOff();
    }
}

// 抽象的电视机状态角色
abstract class TVState {
    // 使用遥控器作为上下文，控制电视机状态的切换
    protected RemoteControlMachine remoteControlMachine;

    public void setRemoteControlMachine(RemoteControlMachine remoteControlMachine) {
        this.remoteControlMachine = remoteControlMachine;
    }

    // 开机
    abstract void powerOn();
    // 关机
    abstract void powerOff();
    // 播放
    abstract void play();
    // 待机
    abstract void standby();
}
// 待机状态
class StandByState extends TVState {
    @Override
    void powerOn() {
        // do nothing
    }

    @Override
    void powerOff() {
        System.out.println("关机...");
        // 使用遥控器设置电视机状态为 关机
        super.remoteControlMachine.setCurrentState(RemoteControlMachine.POWER_OFF_STATE);
        // 执行关机的行为
        super.remoteControlMachine.powerOff();
    }

    @Override
    void play() {
        System.out.println("播放...");
        super.remoteControlMachine.setCurrentState(RemoteControlMachine.PLAY_STATE);
        // 执行播放的行为
        super.remoteControlMachine.play();
    }

    @Override
    void standby() {
        // do nothing
    }
}
// 关机状态
class PowerOffState extends TVState {
    @Override
    void powerOn() {
        System.out.println("开机...");
        // 开机后状态默认为 待机
        super.remoteControlMachine.setCurrentState(RemoteControlMachine.STANDBY_STATE);
        // 执行待机的行为
        super.remoteControlMachine.standby();
    }

    @Override
    void powerOff() {
        // do nothing
    }

    @Override
    void play() {
        // do nothing
    }

    @Override
    void standby() {
        // do nothing
    }
}
// 播放状态
class PlayState extends TVState {
    @Override
    void powerOn() {
        // do nothing
    }

    @Override
    void powerOff() {
        System.out.println("关机...");
        // 使用遥控器设置电视机状态为 关机
        super.remoteControlMachine.setCurrentState(RemoteControlMachine.POWER_OFF_STATE);
        // 执行关机的行为
        super.remoteControlMachine.powerOff();
    }

    @Override
    void play() {
        // do nothing
    }

    @Override
    void standby() {
        System.out.println("待机...");
        // 使用遥控器设置电视机状态为 待机
        super.remoteControlMachine.setCurrentState(RemoteControlMachine.STANDBY_STATE);
        // 执行待机的行为
        super.remoteControlMachine.standby();
    }
}

// 遥控器，扮演上下文角色，负责电视机状态切换
class RemoteControlMachine {
    // 包含电视机的三种状态：待机、关机、播放
    public final static TVState STANDBY_STATE = new StandByState();
    public final static TVState POWER_OFF_STATE = new PowerOffState();
    public final static TVState PLAY_STATE = new PlayState();
    // 标识当前状态
    private TVState currentState;
    // 获取当前状态
    public TVState getCurrentState() {
        return currentState;
    }
    // 设置当前状态，遥控器负责电视机的具体状态切换
    public void setCurrentState(TVState currentState) {
        this.currentState = currentState;
        this.currentState.setRemoteControlMachine(this);
    }

    // 委托给state统一去处理
    public void powerOn() {
        // 当前状态下如何powerOn，由state去确定
        this.currentState.powerOn();
    }
    public void powerOff() {
        this.currentState.powerOff();
    }
    public void play() {
        this.currentState.play();
    }
    public void standby() {
        this.currentState.standby();
    }
}

//// 定义一个电视机状态的枚举
//enum TVStateEnum {
//    // 分别定义待机、关机、播放三种状态
//    STANDBY_STATE(1), POWER_OFF_STATE(2), PLAY_STATE(3);
//    private final int state;
//    private TVStateEnum(int state) {
//        this.state = state;
//    }
//}
//// 定义一个电视机接口
//interface ITelevision {
//    // 开机
//    void powerOn();
//    // 关机
//    void powerOff();
//    // 播放
//    void play();
//    // 待机
//    void standby();
//}
// 电视机的实现类
//class Telev implements ITelevision {
//    // 这里加入电视机的状态字段，构造中传入
//    private TVStateEnum state;
//    public Telev(TVStateEnum state) {
//        this.state = state;
//    }
//
//    public TVStateEnum getState() {
//        return state;
//    }
//
//    public void setState(TVStateEnum state) {
//        this.state = state;
//    }
//
//    // 开机
//    @Override
//    public void powerOn() {
//        switch (this.state) {
//            // 待机状态
//            case STANDBY_STATE:
//                // 待机状态进行开机，没有任何效果，所以这里什么也不做，以下同理
//                break;
//            // 关机状态
//            case POWER_OFF_STATE:
//                // 关机状态进行开机，是允许的，开机之后默认属于standby待机状态
//                System.out.println("开机...");
//                this.setState(TVStateEnum.STANDBY_STATE);
//                break;
//            // 播放状态
//            case PLAY_STATE:
//                // 播放状态进行开机，没有任何效果，所以这里什么也不做，以下同理
//                break;
//            default:
//                break;
//        }
//    }
//
//    // 关机
//    @Override
//    public void powerOff() {
//        switch (this.state) {
//            // 待机状态 & 播放状态 都可以进行关机操作
//            case STANDBY_STATE:
//            case PLAY_STATE:
//                System.out.println("关机...");
//                this.setState(TVStateEnum.POWER_OFF_STATE);
//                break;
//            // 关机状态
//            case POWER_OFF_STATE:
//                break;
//            default:
//                break;
//        }
//    }
//
//    // 播放
//    @Override
//    public void play() {
//        switch (this.state) {
//            // 待机状态
//            case STANDBY_STATE:
//                System.out.println("播放...");
//                this.setState(TVStateEnum.PLAY_STATE);
//                break;
//            // 关机状态
//            case POWER_OFF_STATE:
//                break;
//            // 播放状态
//            case PLAY_STATE:
//                break;
//            default:
//                break;
//        }
//    }
//
//    // 待机
//    @Override
//    public void standby() {
//        switch (this.state) {
//            // 待机状态
//            case STANDBY_STATE:
//                break;
//            // 关机状态
//            case POWER_OFF_STATE:
//                System.out.println("关机...");
//                this.setState(TVStateEnum.POWER_OFF_STATE);
//                break;
//            // 播放状态
//            case PLAY_STATE:
//                System.out.println("待机...");
//                this.setState(TVStateEnum.STANDBY_STATE);
//                break;
//            default:
//                break;
//        }
//    }
//}
//
//// 抽象状态角色
//abstract class State {
//    // 上下文角色，负责状态切换
//    protected Ctx context;
//    public void setContext(Ctx context) {
//        this.context = context;
//    }
//    // 状态的公共行为，需要子类自行实现其状态对应的行为
//    abstract void handle1();
//    abstract void handle2();
//}
//// 具体状态1
//class ConcreteState1 extends State {
//
//    @Override
//    void handle1() {
//        // 本状态ConcreteState1时的业务逻辑
//    }
//    @Override
//    void handle2() {
//        // 设置当前状态为state2
//        super.context.setCurrentState(Ctx.state2);
//        super.context.request2();
//    }
//}
//// 具体状态2
//class ConcreteState2 extends State {
//    @Override
//    void handle1() {
//        super.context.setCurrentState(Ctx.state1);
//        super.context.request1();
//    }
//    @Override
//    void handle2() {
//        // 本状态ConcreteState2时的业务逻辑
//    }
//}
//class Ctx {
//    // 上下文角色一般会包含全部状态，使用静态常量修饰
//    public final static State state1 = new ConcreteState1();
//    public final static State state2 = new ConcreteState2();
//    // 定义上下文保存的当前状态
//    private State currentState;
//
//    public State getCurrentState() {
//        return currentState;
//    }
//
//    // 设置当前状态
//    public void setCurrentState(State currentState) {
//        this.currentState = currentState;
//        // 初始化state中的上下文
//        this.currentState.setContext(this);
//    }
//
//    // 上下文一般包含抽象状态中的所有行为，然后委托给state
//    public void request1() {
//        this.currentState.handle1();
//    }
//    public void request2() {
//        this.currentState.handle2();
//    }
//}