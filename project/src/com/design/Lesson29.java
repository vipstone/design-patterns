package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 传输对象模式
 */
public class Lesson29 {
    public static void main(String[] args) {
        //------------- 传输对象模式基础示例 -------------
        // 创建业务对象，获取到数据（伪代码，初始化业务对象时会填充数据）
        BusinessObject businessObject = new BusinessObject();
        // 循环打印所有业务数据
        for (TransferObject t : businessObject.getList()) {
            System.out.println("Id=" + t.getId() + " | " + "Name=" + t.getName());
        }
        // 更新数据
        TransferObject transferObject = businessObject.getList().get(0);
        System.out.println("原名称：" + transferObject.getName());
        transferObject.setName("LaoWang");
        businessObject.update(transferObject);
        // 打印修改的数据
        System.out.println("修改后的名称：" + businessObject.getList().get(0).getName());
        //------------- 传输对象模式——快递示例 -------------
        // 创建快递业务类
        Express express = new Express();
        // 创建快递物品
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setMobile("18000000008");
        goodsInfo.setName("设计模式34讲");
        // 添加快递
        express.insert(goodsInfo);
        // 打印所有快递信息
        express.getAllGoods().forEach((goods) -> {
            System.out.println("手机号：" + goods.getMobile() + " | 快递物品：" + goods.getName());
        });
    }
}

//------------- 传输对象模式——快递示例 -------------

/**
 * 物品表
 */
class GoodsInfo {
    private String mobile;
    private String name;

    public GoodsInfo() {
    }

    public GoodsInfo(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 快递业务类
 */
class Express {
    // 所有的快递物品存储容器
    private HashMap<String, String> goods;

    public Express() {
        // 模拟数据
        goods = new HashMap<>();
        goods.put("18000000001", "Java面试全解析");
        goods.put("18000000002", "MySQL面试金典");
    }

    /**
     * 邮寄物品（添加订单）
     * @param goodsInfo 邮寄的物品
     */
    public void insert(GoodsInfo goodsInfo) {
        goods.put(goodsInfo.getMobile(), goodsInfo.getName());
    }

    /**
     * 查询所有的邮件
     */
    public List<GoodsInfo> getAllGoods() {
        List<GoodsInfo> list = new ArrayList<>();
        GoodsInfo goodsInfo;
        goods.forEach((k, v) -> {
            list.add(new GoodsInfo(k, v));
        });
        return list;
    }
}

//------------- 传输对象模式基础示例 -------------

/**
 * 传输对象，只提供传输对象的 get/set 方法
 */
class TransferObject {
    private int id;
    private String name;

    public TransferObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 业务对象，提供业务数据
 */
class BusinessObject {
    // 数据来源的存储容器
    private List<TransferObject> list;

    public BusinessObject() {
        // 填充业务数据（伪代码，模拟数据产生）
        list = new ArrayList<>();
        list.add(new TransferObject(0, "Java"));
        list.add(new TransferObject(1, "SQL"));
    }

    /**
     * 获取所有业务数据
     */
    public List<TransferObject> getList() {
        return list;
    }

    /**
     * 根据下标查询相应的数据
     * @param index 下标
     * @return 返回单条数据
     */
    public TransferObject getDataById(int index) {
        return list.get(index);
    }

    /**
     * 修改业务数据
     * @param transferObject
     */
    public void update(TransferObject transferObject) {
        list.get(transferObject.getId()).setName(transferObject.getName());
    }
}