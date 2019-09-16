package com.design;

class Lesson6 {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
    }
}
// 产品类（拿建造大楼举例）
class Product {
    private String ground; // 地基
    private String cement; // 水泥
    private String roof; // 楼顶
    Product() {
    }
    String getGround() {
        return ground;
    }
    void setGround(String ground) {
        this.ground = ground;
    }
    String getCement() {
        return cement;
    }
    void setCement(String cement) {
        this.cement = cement;
    }
    String getRoof() {
        return roof;
    }
    void setRoof(String roof) {
        this.roof = roof;
    }
}
// 抽象建造者模式
interface Builder {
    // 一般来说，有多少零配件，就有多少建造方法
    void buildGround();
    void buildCement();
    void buildRoof();
    // 返回产品类
    Product buildProduct();
}
// 建造者的实现类
class ConcreteBuilder implements Builder{
    private final Product product = new Product();
    @Override
    public void buildGround() {
        System.out.println("build地基");
        product.setGround("build地基");
    }
    @Override
    public void buildCement() {
        System.out.println("build水泥");
        product.setGround("build水泥");
    }
    @Override
    public void buildRoof() {
        System.out.println("build楼顶");
        product.setGround("build楼顶");
    }
    @Override
    public Product buildProduct() {
        System.out.println("建造完毕!");
        return product;
    }
}
// 监工，负责指挥 builder 如何建造即如何调度
class Director {
    private Builder builder;

    Director(Builder builder) {
        this.builder = builder;
    }
    public Product construct() {
        // 构建顺序：建造水泥->地基->楼顶
        builder.buildCement();
        builder.buildGround();
        builder.buildRoof();
        return builder.buildProduct();
    }
}
