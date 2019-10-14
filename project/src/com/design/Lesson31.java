package com.design;

/**
 * 前端控制器模式
 **/
public class Lesson31 {
    public static void main(String[] args) {
        //------------- 前端控制器模式基础示例 -------------
        // 创建前端控制器
        FrontController frontController = new FrontController();
        // 执行 user 请求
        frontController.dispatchRequest("user");
        // 执行 loginout 请求
        frontController.dispatchRequest("loginout");
        //------------- 前端控制器乘车示例 -------------
        // 创建控制器，统一验票口
        TrainFrontController trainFrontController = new TrainFrontController();
        // 验证左边大厅的车票
        trainFrontController.dispatchPeople("left");
        // 验证右边大厅的车票
        trainFrontController.dispatchPeople("right");
    }
}

//------------- 前端控制器乘车示例演示 -------------

/**
 * 火车统一检票站（前端控制器）
 **/
class TrainFrontController {
    private TrainDispatcher trainDispatcher;

    public TrainFrontController() {
        trainDispatcher = new TrainDispatcher();
    }

    /**
     * @return boolean 检票结果
     * @Description 检票，检查车票的有效性
     * @Param ticket 车票
     **/
    private boolean ticketCheck(String ticket) {
        System.out.println("验证车票：" + ticket);
        return true;
    }

    /**
     * @Description 统一处理方法
     * @Param ticket 车票
     **/
    public void dispatchPeople(String ticket) {
        // 验票
        if (ticketCheck(ticket)) {
            trainDispatcher.dispatch(ticket);
        }
    }
}

/**
 * 调度器根据车票导流乘客到不同的乘车大厅
 **/
class TrainDispatcher {
    private LeftHall leftHall;
    private RightHall rightHall;

    public TrainDispatcher() {
        leftHall = new LeftHall();
        rightHall = new RightHall();
    }

    /**
     * @Description 调度方法
     * @Param ticket 车票
     **/
    public void dispatch(String ticket) {
        if (ticket.equalsIgnoreCase("left")) {
            leftHall.toHall();
        } else {
            rightHall.toHall();
        }
    }
}

/**
 * 左边候车大厅
 **/
class LeftHall {
    public void toHall() {
        System.out.println("进入左边候车大厅");
    }
}

/**
 * 右边候车大厅
 **/
class RightHall {
    public void toHall() {
        System.out.println("进入右边候车大厅");
    }
}

//------------- 以下代码为前端控制器基础示例演示 -------------

/**
 * 前端控制器，给程序提供统一的请求入口
 **/
class FrontController {
    // 创建调度器
    private Dispatcher dispatcher;

    public FrontController() {
        dispatcher = new Dispatcher();
    }

    /**
     * @Description 记录每一个请求
     * @Param request 请求信息
     **/
    private void requestLog(String request) {
        System.out.println("请求命令日志记录：" + request);
    }

    /**
     * @Description 统一请求方法
     * @Param request 请求信息
     **/
    public void dispatchRequest(String request) {
        // 记录每一个请求
        requestLog(request);
        // 登录权限验证
        if (isAuthenticUser()) {
            dispatcher.dispatch(request);
        }
    }

    /**
     * 登录权限效验（伪代码，全部返回 true）
     **/
    private boolean isAuthenticUser() {
        System.out.println("用户权限验证通过");
        return true;
    }
}

/**
 * 调度器，把请求调度到相应的处理程序
 **/
class Dispatcher {
    private UserView userView;
    private LoginOutView loginOutView;

    public Dispatcher() {
        userView = new UserView();
        loginOutView = new LoginOutView();
    }

    /**
     * @Description 调度方法
     * @Param request请求命令
     **/
    public void dispatch(String request) {
        if (request.equalsIgnoreCase("user")) {
            userView.show();
        } else if (request.equalsIgnoreCase("loginout")) {
            loginOutView.show();
        }
    }
}

/**
 * 个人主页，信息显示
 **/
class UserView {
    public void show() {
        System.out.println("我是个人主页");
    }
}

/**
 * 登录退出页，信息显示
 **/
class LoginOutView {
    public void show() {
        System.out.println("我是登录退出页");
    }
}


