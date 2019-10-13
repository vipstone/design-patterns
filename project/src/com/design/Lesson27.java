package com.design;

public class Lesson27 {
    public static void main(String[] args) {
        // 打开视图
        View view = new View();
        // 执行查询，并展现结果
        view.show();

        //---------- 根据关键字查询简介实例 -------------
        // 打开页面
        MvcView mvcView = new MvcView();
        // 输入书名：Java
        mvcView.name = "Java";
        // 点击查询，执行相关流程并展示结果
        mvcView.find();
        // 输入书名：MySQL
        mvcView.name = "MySQL";
        // 点击查询，执行相关流程并展示结果
        mvcView.find();
    }
}

/**
 * 视图层
 **/
class View {
    public void show() {
        System.out.println("I'm View.");
        Controller.doController();
    }
}

/**
 * 控制器（层）
 **/
class Controller {
    public static void doController() {
        System.out.println("I'm Controller.");
        Model.method();
    }
}

/**
 * 模型层
 **/
class Model {
    public static void method() {
        System.out.println("I'm Model.");
    }
}

/**
 * 模型层
 **/
class MvcModel {
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

/**
 * 视图层
 **/
class MvcView {
    public String name;

    public MvcView() {
        System.out.println("页面展示");
    }

    // 接收用户请求，调用控制器
    public void find() {
        // 调用控制器，输出结果
        System.out.println("输出结果：" + new MvcController().getDesc(name));
    }
}

/**
 * 控制器（层）
 **/
class MvcController {
    /**
     * @return 简介内容
     * @Description 根据名称获取简介
     * @Param name 名称
     **/
    public String getDesc(String name) {
        // 返回的最终结果
        String result = "暂无";
        // 组织数据
        MvcModel mvcModel = MvcDB.findByName(name);
        if (mvcModel != null) {
            result = mvcModel.getDesc();
        }
        return result;
    }
}

/**
 * 数据层
 **/
class MvcDB {
    /**
     * @return 模型对象数据
     * @Description 返回数据
     * @Param [name]
     **/
    public static MvcModel findByName(String name) {
        MvcModel model = null;
        switch (name) {
            case "Java":
                model = new MvcModel();
                model.setName("Java面试全解析");
                model.setDesc("这是一本包含了 505 道面试题的 Java 书籍。");
                break;
            case "MySQL":
                model = new MvcModel();
                model.setName("MySQL面试金典");
                model.setDesc("这是一本包含了 208 道面试题的 MySQL 书籍。");
                break;
            default:
                break;
        }
        return model;
    }
}