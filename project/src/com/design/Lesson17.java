package com.design;

/**
 * 命令模式
 **/

// 客户端
class Client {
    public static void main(String[] args) {
        // 创建接收者
        Receiver receiver = new Receiver();
        // 创建命令对象，设定接收者
        Command command = new ConcreteCommand(receiver);
        // 创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(command);
        // 执行方法
        invoker.action();
    }
}

// 接收者
class Receiver {
    public void doSomething() {
        System.out.println("执行业务逻辑");
    }
}

// 命令类
interface Command {
    void execute();
}

// 具体命令类
class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.doSomething();
    }
}

// 请求者类
class Invoker {
    // 持有命令对象
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    // 请求方法
    public void action() {
        this.command.execute();
    }
}





