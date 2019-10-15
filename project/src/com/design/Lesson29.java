package com.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 传输对象模式
 */
public class Lesson29 {
    public static void main(String[] args) {
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
    }
}

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
    List<TransferObject> list;

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