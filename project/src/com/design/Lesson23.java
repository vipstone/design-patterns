package com.design;

/**
 * 空对象模式
 */
public class Lesson23 {
    public static void main(String[] args) {
        AbstractObject object1 = ObjectFactory.creator("Java");
        AbstractObject object2 = ObjectFactory.creator("MySQL");
        AbstractObject object3 = ObjectFactory.creator("SQL");
        System.out.println(object1.getName());
        System.out.println(object2.getName());
        System.out.println(object3.getName());
    }
}

/**
 * 抽象对象
 */
abstract class AbstractObject {
    String name;

    abstract String getName();

    abstract boolean isNull();
}

/**
 * 非空对象
 */
class RealObject extends AbstractObject {
    public RealObject(String name) {
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
    public static AbstractObject creator(String name) {
        AbstractObject result = null;
        switch (name) {
            case "Java":
                result = new RealObject("Java");
                break;
            case "SQL":
                result = new RealObject("SQL");
                break;
            default:
                result = new NullObject();
                break;
        }
        return result;
    }
}