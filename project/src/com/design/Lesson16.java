package com.design;

import java.util.ArrayList;

public class Lesson16 {

    public static void main(String[] args) {
        ArrayList<IRequest> arrayList = new ArrayList<>();
        arrayList.add(new Request(Handler.Difficulty_LEVEL_1_REQUEST, "1+1=？"));
        arrayList.add(new Request(Handler.Difficulty_LEVEL_2_REQUEST, "3*4=？"));
        arrayList.add(new Request(Handler.Difficulty_LEVEL_3_REQUEST, "87834*765=？"));
        arrayList.add(new Request(4, "765343/767*232=？"));
        // 定义三个责任人对象
        Handler primary = new Primary();
        Handler middle = new Middle();
        Handler senior = new Senior();
        // 设置链的调用顺序
        primary.setNextHandler(middle);
        middle.setNextHandler(senior);
        for (IRequest request : arrayList) {
            // 责任链中处理该请求
            primary.HandleMessage(request);
        }
    }

}

// 请求接口
interface IRequest {
    // 获取请求级别
    int getRequestLevel();

    // 获取要请求的内容
    String getRequest();
}

class Request implements IRequest {
    /**
     * 难度级别1--初级工程师解决
     * 难度级别2--中级工程师解决
     * 难度级别3--高级工程师解决
     */
    private int level;
    // 要请求的内容或要解决的问题
    private String request = "";

    Request(int _level, String _request) {
        this.level = _level;
        switch (this.level) {
            case 1:
                this.request = "难度级别为1的请求是：" + _request;
                break;
            case 2:
                this.request = "难度级别为2的请求是：" + _request;
                break;
            case 3:
                this.request = "难度级别为3的请求是：" + _request;
                break;
        }
    }

    @Override
    public int getRequestLevel() {
        return this.level;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}

// 初级难度的责任人
class Primary extends Handler {
    // 初级工程师可以处理难度等级为1的请求
    Primary() {
        super(Handler.Difficulty_LEVEL_1_REQUEST);
    }

    // 处理结果
    @Override
    protected void response(IRequest request) {
        System.out.println("--------难度级别为1的请求-------");
        System.out.println(request.getRequest());
        System.out.println("初级工程师处理结果: 已处理\n");
    }
}

class Middle extends Handler {
    // 中级工程师可以处理难度等级为2的请求
    Middle() {
        super(Handler.Difficulty_LEVEL_2_REQUEST);
    }

    // 处理结果
    @Override
    protected void response(IRequest request) {
        System.out.println("--------难度级别为2的请求-------");
        System.out.println(request.getRequest());
        System.out.println("中级工程师处理结果: 已处理\n");
    }
}

class Senior extends Handler {
    // 高级工程师可以处理难度级别为3的请求
    Senior() {
        super(Handler.Difficulty_LEVEL_3_REQUEST);
    }

    // 处理结果
    @Override
    protected void response(IRequest request) {
        System.out.println("--------难度级别为3的请求-------");
        System.out.println(request.getRequest());
        System.out.println("高级工程师处理结果: 已处理\n");
    }
}

abstract class Handler {
    final static int Difficulty_LEVEL_1_REQUEST = 1; // 难度级别为1
    final static int Difficulty_LEVEL_2_REQUEST = 2; // 难度级别为2
    final static int Difficulty_LEVEL_3_REQUEST = 3; // 难度级别为3
    // 能处理的级别
    private int level = 0;
    // 责任传递，下一个责任人是谁
    private Handler nextHandler;

    // 每个类都要说明一下自己能处理哪些请求
    Handler(int level) {
        this.level = level;
    }

    // 负责Request的请求处理，final关键字声明不允许被子类覆盖
    final void HandleMessage(IRequest request) {
        // 如果请求级别小于可以处理的级别就直接处理，比如：高级工程师也可以处理难度级别为1的请求
        if (request.getRequestLevel() <= this.level) {
            this.response(request);
        } else {
            if (this.nextHandler != null) { // 有后续环节，请求传递
                this.nextHandler.HandleMessage(request);
            } else { // 无后续环节了，按照不同意处理
                System.out.println("--------难度级别为" + request.getRequestLevel() + "的请求-------");
                System.out.println("---抱歉，没有工程师可以处理---\n");
            }
        }
    }

    /*
     * 如果不属于你处理的请求，你应该让请求路由到下一个环节的责任人
     */
    void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 有请示那当然要回应
    protected abstract void response(IRequest request);
}
