package com.design;

import java.util.Optional;

/**
 * 空对象模式
 */
public class Lesson23 {
    public static void main(final String[] args) {
        // ------------- 1.空对象模式基础示例 Start -------------
        final AbstractObject object1 = ObjectFactory.creator("Java");
        final AbstractObject object2 = ObjectFactory.creator("MySQL");
        final AbstractObject object3 = ObjectFactory.creator("SQL");
        System.out.println(object1.getName());
        System.out.println(object2.getName());
        System.out.println(object3.getName());
        // ------------- 空对象模式基础示例 End -------------

        // ------------- 2.空对象模式实例 Start -------------
        final AbstractGoods goods1 = GoodsFactory.find("001");
        final AbstractGoods goods2 = GoodsFactory.find("003");
        goods1.show();
        goods2.show();
        // ------------- 空对象模式实例 End -------------

        // JDK 8 Optional 对象判空示例
        // 具体对象
        User concreteUser = new User(new Address(new Country("china")));
        // 空对象
        User nullUser = new User(null);
        // 具体对象编码获取
        String concreteIsocode = Optional.ofNullable(concreteUser)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getIsocode())
                .orElse("暂无").toUpperCase();
        // 空对象编码获取
        String nullIsocode = Optional.ofNullable(nullUser)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getIsocode())
                .orElse("暂无").toUpperCase();
        System.out.println("Concrete User：" + concreteIsocode);
        System.out.println("Null User：" + nullIsocode);
    }
}

/**
 * 用户类
 **/
class User {
    public User(Address address) {
        this.address = address;
    }

    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

/**
 * 地址类
 **/
class Address {
    public Address(Country country) {
        this.country = country;
    }

    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

/**
 * 国际编码类
 **/
class Country {
    public Country(String isocode) {
        this.isocode = isocode;
    }

    private String isocode;

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
}

// ------------- 空对象模式实例 Start -------------

/**
 * 抽象（商品）对象
 **/
abstract class AbstractGoods {
    public String isbn; // 商品编码
    public String name;
    public Double price;

    public abstract void show();
}

/**
 * 具体商品
 **/
class ConcreteGoods extends AbstractGoods {
    public ConcreteGoods(String isbn, String name, Double price) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
    }

    @Override
    public void show() {
        System.out.println("商品名：" + this.name + "，价格：" + this.price + "元");
    }
}

/**
 * 空商品
 **/
class NullGoods extends AbstractGoods {
    @Override
    public void show() {
        System.out.println("商品信息暂无！");
    }
}

/**
 * 商品工厂
 **/
class GoodsFactory {
    public static AbstractGoods find(final String isbn) {
        AbstractGoods result = null;
        switch (isbn) {
            case "001":
                result = new ConcreteGoods("001", "Java面试全解析", 69.0);
                break;
            case "002":
                result = new ConcreteGoods("002", "MySQL面试金典", 19.0);
                break;
            default:
                result = new NullGoods();
                break;
        }
        return result;
    }
}

// ------------- 空对象模式实例 End -------------

// ------------- 空对象模式基础示例 Start -------------

/**
 * 抽象对象
 */
abstract class AbstractObject {
    String name;

    abstract String getName();

    abstract boolean isNull();
}

/**
 * 具体对象
 */
class ConcreteObject extends AbstractObject {
    public ConcreteObject(final String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    boolean isNull() {
        return false;
    }
}

/**
 * 空对象
 */
class NullObject extends AbstractObject {

    @Override
    String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    boolean isNull() {
        return true;
    }
}

/**
 * 对象生成工厂
 */
class ObjectFactory {
    public static AbstractObject creator(final String name) {
        AbstractObject result = null;
        switch (name) {
            case "Java":
                result = new ConcreteObject("Java");
                break;
            case "SQL":
                result = new ConcreteObject("SQL");
                break;
            default:
                result = new NullObject();
                break;
        }
        return result;
    }
}
// ------------- 空对象模式基础示例 End -------------