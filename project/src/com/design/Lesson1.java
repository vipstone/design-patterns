package com.design;

import java.util.ArrayList;

public class Lesson1 {

    public static void main(String[] args) {
        // 开闭原则测试
        Shop4S.main(null);
        Shop4SNew.main(null);
        // 里氏替换原则测试
        Person person = new Person();
        person.feedAnimal(new Rabbit());
        person.walkAnimal();
    }
}
/***************** 开闭原则 start *******************/

// 添加子类 FinanceBenzCar 后的 Shop4S 类
class Shop4SNew {

    private final static ArrayList<ICar> carList = new ArrayList<ICar>();
    private final static ArrayList<ICar> financeCarList = new ArrayList<ICar>();
    // 使用static代码块模拟数据初始化操作
    static {
        carList.add(new BenzCar("梅赛德斯-迈巴赫S级轿车",138));
        carList.add(new BenzCar("梅赛德斯-AMG S 63 L 4MATIC+", 230));
        carList.add(new BenzCar("梅赛德斯-奔驰V级", 50));
        financeCarList.add(new FinanceBenzCar("梅赛德斯-迈巴赫S级轿车",138));
        financeCarList.add(new FinanceBenzCar("梅赛德斯-AMG S 63 L 4MATIC+", 230));
        financeCarList.add(new FinanceBenzCar("梅赛德斯-奔驰V级", 50));
    }

    public static void main(String[] args) {
        System.out.println("4s店售车记录（不含金融服务费）:");
        for (ICar car: carList){
            System.out.println("车名：" + car.getName() + "\t价格：" + car.getPrice() + "万元");
        }
        System.out.println("\n4s店售车记录（包含金融服务费）:");
        for (ICar car: financeCarList) {
            System.out.println("车名：" + car.getName() + "\t价格：" + car.getPrice() + "万元");
        }
    }

}

// 模拟4s店销售（修改前）
class Shop4S {

    private final static ArrayList<ICar> carList = new ArrayList<ICar>();
    // 使用static代码块模拟数据初始化操作
    static {
        carList.add(new BenzCar("梅赛德斯-迈巴赫S级轿车",138));
        carList.add(new BenzCar("梅赛德斯-AMG S 63 L 4MATIC+", 230));
        carList.add(new BenzCar("梅赛德斯-奔驰V级", 50));
    }

    public static void main(String[] args) {
        System.out.println("4s店售车记录:");
        for (ICar car: carList){
            System.out.println("车名：" + car.getName() + "\t价格：" + car.getPrice() + "万元");
        }
    }

}

// 增加子类FinanceBenzCar，覆写父类BenzCar的getPrice方法，实现金融服务费相关逻辑处理
class FinanceBenzCar extends BenzCar{
    FinanceBenzCar(String _name, int _price) {
        super(_name, _price);
    }

    // 覆写价格信息
    @Override
    public int getPrice() {
        // 获取原价
        int selfPrice = super.getPrice();
        int financePrice = 0;
        if (selfPrice >= 100) {
            financePrice = selfPrice + selfPrice * 5 / 100; // 收取5%的金融服务费
        } else if (selfPrice >= 50) {
            financePrice = selfPrice + selfPrice * 2 / 100; // 收取2%的金融服务费
        } else {
            financePrice = selfPrice; // 其余不收取服务费
        }
        return financePrice;
    }
}

// 车接口
interface ICar {
    // 车名
    public String getName();
    // 车价格
    public int getPrice();
}

// 奔驰车
class BenzCar implements ICar{
    // 车名
    private String name;
    // 车价格
    private int price;

    // 通过构造方法实例化
    public BenzCar(String _name, int _price) {
        this.name = _name;
        this.price = _price;
    }

    // 获取车名
    @Override
    public String getName() {
        return this.name;
    }
    // 获取车价格
    @Override
    public int getPrice() {
        return this.price;
    }
}

/***************** 里氏替换原则 start *******************/
class Person {
    private AbstractAnimal animal;

    public void feedAnimal(AbstractAnimal _animal) {
        this.animal = _animal;
    }

    public void walkAnimal(){
        System.out.println("人开始溜动物...");
        animal.dance();
    }
}
abstract class AbstractAnimal{
    abstract void dance();
}
class Rabbit extends AbstractAnimal{
    @Override
    void dance() {
        System.out.println("小白兔跳舞...");
    }
}

/***************** 依赖倒置原则 start *******************/
interface Shop{
    String sell();
}
class Customer {
    // 参数尽量使用接口传参
    public void shopping (Shop shop) {
        System.out.println(shop.sell());
    }
}
class YanTaShop implements Shop {
    @Override
    public String sell() {
        // do something
        return null;
    }
}
