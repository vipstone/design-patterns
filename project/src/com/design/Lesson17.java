package com.design;

/**
 * 命令模式
 **/

//// ---------------------- 基础命令模式演示 Start ------------------------
//// 客户端
//class Client {
//    public static void main(String[] args) {
//        // 创建接收者
//        Receiver receiver = new Receiver();
//        // 创建命令对象，设定接收者
//        Command command = new ConcreteCommand(receiver);
//        // 创建请求者，把命令对象设置进去
//        Invoker invoker = new Invoker(command);
//        // 执行方法
//        invoker.action();
//    }
//}
//
//// 接收者
//class Receiver {
//    public void doSomething() {
//        System.out.println("执行业务逻辑");
//    }
//}
//
//// 命令类
//interface Command {
//    void execute();
//}
//
//// 具体命令类
//class ConcreteCommand implements Command {
//    private Receiver receiver;
//
//    public ConcreteCommand(Receiver receiver) {
//        this.receiver = receiver;
//    }
//
//    public void execute() {
//        this.receiver.doSomething();
//    }
//}
//
//// 请求者类
//class Invoker {
//    // 持有命令对象
//    private Command command;
//
//    public Invoker(Command command) {
//        this.command = command;
//    }
//
//    // 请求方法
//    public void action() {
//        this.command.execute();
//    }
//}
//// ---------------------- 基础命令模式演示 End ------------------------

// ---------------------- 命令模式示例演示 Start ------------------------
interface Command {
    void execute();
}

// 电视机
class TV {
    public void open() {
        System.out.println("打开电视机");
    }

    public void close() {
        System.out.println("关闭电视机");
    }

    public void change() {

        System.out.println("切换电视频道");
    }

}

// 遥控功能
class OpenTvCommand implements Command {
    private TV tv;

    public OpenTvCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.open();
    }
}

class ChangeTvCommand implements Command {
    private TV tv;

    public ChangeTvCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.change();
    }
}

class CloseTvCommand implements Command {
    private TV tv;

    public CloseTvCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.close();
    }
}

// 遥控器
class TvRemote {
    private Command openTvCommand;
    private Command closeTvCommand;
    private Command changeTvCommand;

    public TvRemote(Command openTvCommand, Command closeTvCommand, Command changeTvCommand) {
        this.openTvCommand = openTvCommand;
        this.closeTvCommand = closeTvCommand;
        this.changeTvCommand = changeTvCommand;
    }

    // 打开电视
    public void open() {
        openTvCommand.execute();
    }

    // 关闭电视
    public void close() {
        closeTvCommand.execute();
    }

    // 换频道
    public void change() {
       changeTvCommand.execute();
    }
}
class Client{
    public static void main(String[] args) {
        TV tv = new TV();
        Command openTvCommand = new OpenTvCommand(tv);
        Command closeTvCommand = new CloseTvCommand(tv);
        Command changeTvCommand = new ChangeTvCommand(tv);
        TvRemote control = new TvRemote(openTvCommand,closeTvCommand,changeTvCommand);
        control.open();
        control.change();
        control.close();
    }
}
// ---------------------- 命令模式示例演示 End ------------------------


