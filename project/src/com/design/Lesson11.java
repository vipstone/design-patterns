package com.design;

import java.util.ArrayList;

public class Lesson11 {
    public static void main(String[] args) {
        // 创建根节点及其子节点
        Composite root = new Composite("综合实验室");
        root.add(new Leaf("综合设备1"));
        root.add(new Leaf("综合设备2"));

        // 创建二级节点及其子节点
        Composite branchLevel21 = new Composite("化学实验室");
        branchLevel21.add(new Leaf("试管"));
        branchLevel21.add(new Leaf("烧杯"));
        branchLevel21.add(new Leaf("锥形瓶"));
        root.add(branchLevel21);

        // 并列的二级节点
        Composite branchLevel22 = new Composite("物理实验室");
        branchLevel22.add(new Leaf("单刀单至开关设备"));
        branchLevel22.add(new Leaf("电磁箱"));

        Composite branchLevel221 = new Composite("精密仪器实验组");
        branchLevel221.add(new Leaf("精密光学测量仪"));
        branchLevel221.add(new Leaf("精密机床"));
        branchLevel22.add(branchLevel221);

        root.add(branchLevel22);

        root.display(1);
    }
}

// 抽象构件Component：可以是抽象接口也可以是抽象类
abstract class Component {
    private String name; // 设备或部门名称

    public String getName() {
        return name;
    }

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component); // 采购设备或添加子部门

    public abstract void remove(Component component); // 移除设备或子部门

    public abstract void display(int depth); // 查询该节点下所有“设备”和“部门”
}

// 叶子节点Leaf类：树叶节点，模拟某个单台设备
class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("叶子节点（设备）不能挂载设备");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("叶子节点（设备）不能移除设备");
    }

    @Override
    public void display(int depth) {
        //输出树形结构的叶子节点，这里直接输出设备名称
        for (int i = 0; i < depth; i++) {
            System.out.print("*");
        }
        System.out.println(getName());
    }
}

// 复合组件构建类，模拟某个组织部门
class Composite extends Component {

    public Composite(String name) {
        super(name);
    }

    // 构建一个容器，用来保存该节点下所有的“设备”和“组织”
    private ArrayList<Component> componentArrayList = new ArrayList<>();

    // 采购设备时，只需要将“设备”加入到已有的“设备”列表
    @Override
    public void add(Component component) {
        this.componentArrayList.add(component);
    }

    // 移除设备时，只需要将“设备”从已有的“设备”列表中移除
    @Override
    public void remove(Component component) {
        this.componentArrayList.remove(component);
    }

    // 该容器内部，递归查询depth深度的节点下的列表内容
    @Override
    public void display(int depth) {
        // 输出树形结构（根据depth深度模拟输出多少个-）
        for (int i = 0; i < depth; i++) {
            System.out.print("*");
        }
        System.out.println(getName());
        // 递归显示
        for (Component component : componentArrayList) {
            component.display(depth + 1);
        }
    }
}