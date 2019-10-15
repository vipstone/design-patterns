package com.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器定位模式
 **/
public class Lesson33 {
    public static void main(String[] args) {
        //------------- 服务器定位模式基础示例 -------------
        // 获取并执行服务 A
        Service service = ServiceLocator.getService("ServiceA");
        service.execute();
        // 获取并执行服务 B
        service = ServiceLocator.getService("ServiceB");
        service.execute();
        // 获取并执行服务 A（从缓存中获取）
        service = ServiceLocator.getService("ServiceA");
        service.execute();
        // 获取并执行服务 B（从缓存中获取）
        service = ServiceLocator.getService("ServiceB");
        service.execute();
        //------------- 服务器定位模式—枪声音示例 -------------
        Gun gun = GunLocator.getGun("狙击枪");
        gun.sound();
        gun = GunLocator.getGun("机关枪");
        gun.sound();
        gun = GunLocator.getGun("狙击枪");
        gun.sound();
        gun = GunLocator.getGun("机关枪");
        gun.sound();
    }
}
//------------- 服务器定位模式—枪击声音示例 -------------

/**
 * 枪（服务）接口，用于定义统一行为
 **/
interface Gun {
    // 枪械名称
    public String getName();

    // 声音
    public void sound();
}

/**
 * 狙击枪
 **/
class SniperRifle implements Gun {

    @Override
    public String getName() {
        return "狙击枪";
    }

    @Override
    public void sound() {
        System.out.println("狙击枪：通通通");
    }
}

/**
 * 机关枪
 **/
class MachineGun implements Gun {

    @Override
    public String getName() {
        return "机关枪";
    }

    @Override
    public void sound() {
        System.out.println("机关枪：突突突");
    }
}

/**
 * 创建枪械（的服务）
 **/
class CreateGun {
    public Gun lookup(String name) {
        if (name.equalsIgnoreCase("狙击枪")) {
            return new SniperRifle();
        } else if (name.equalsIgnoreCase("机关枪")) {
            return new MachineGun();
        }
        return null;
    }
}

/**
 * 枪械缓存类
 **/
class GunCache {
    // 用户存储缓存的容器
    private List<Gun> guns;

    public GunCache() {
        // 初始化缓存容器
        guns = new ArrayList<>();
    }

    /**
     * @return Gun 具体的枪
     * @Description 获取缓存
     * @Param name 枪名
     **/
    public Gun getGun(String name) {
        for (Gun gun : guns) {
            if (gun.getName().equalsIgnoreCase(name)) {
                System.out.println("从缓存中获取到对象：" + name);
                return gun;
            }
        }
        return null;
    }

    /**
     * @Description 给缓存中添加枪的信息
     * @Param Gun 具体的枪
     **/
    public void addService(Gun gun) {
        guns.add(gun);
    }
}

/**
 * 服务定位器，获取服务的统一入口
 **/
class GunLocator {
    private static GunCache gunCache;

    static {
        // 初始化缓存类
        gunCache = new GunCache();
    }

    /**
     * @return Gun 具体的服务
     * @Description 获取具体的服务
     * @Param name 枪名
     **/
    public static Gun getGun(String name) {
        // 先从缓存中获取枪信息
        Gun gun = gunCache.getGun(name);
        if (gun != null) {
            return gun;
        }
        // 根据名称找到相应枪信息
        CreateGun createGun = new CreateGun();
        gun = createGun.lookup(name);
        // 把枪信息放入缓存
        gunCache.addService(gun);
        return gun;
    }
}

//------------- 服务器定位模式基础示例 -------------

/**
 * 服务接口
 **/
interface Service {
    public String getName();

    public void execute();
}

/**
 * 具体服务 A
 **/
class ServiceA implements Service {

    @Override
    public String getName() {
        return "ServiceA";
    }

    @Override
    public void execute() {
        System.out.println("执行服务 A");
    }
}

/**
 * 具体服务 B
 **/
class ServiceB implements Service {

    @Override
    public String getName() {
        return "ServiceB";
    }

    @Override
    public void execute() {
        System.out.println("执行服务 B");
    }
}

/**
 * 创建具体的服务（根据名称），提供给服务器定位器使用，普通用户不能直接使用
 **/
class InitialContext {
    public Service lookup(String name) {
        if (name.equalsIgnoreCase("servicea")) {
            return new ServiceA();
        } else if (name.equalsIgnoreCase("serviceb")) {
            return new ServiceB();
        }
        return null;
    }
}

/**
 * 缓存类，用户缓存服务
 **/
class Cache {
    // 用户存储缓存的容器
    private List<Service> services;

    public Cache() {
        // 初始化缓存容器
        services = new ArrayList<>();
    }

    /**
     * @return Service 具体的服务
     * @Description 获取缓存
     * @Param name 服务名称
     **/
    public Service getService(String name) {
        for (Service service : services) {
            if (service.getName().equalsIgnoreCase(name)) {
                System.out.println("从缓存中获取到对象：" + name);
                return service;
            }
        }
        return null;
    }

    /**
     * @Description 给缓存中添加服务
     * @Param service 具体的服务
     **/
    public void addService(Service service) {
        services.add(service);
    }
}

/**
 * 服务定位器，获取服务的统一入口
 **/
class ServiceLocator {
    private static Cache cache;

    static {
        // 初始化缓存类
        cache = new Cache();
    }

    /**
     * @return Service 具体的服务
     * @Description 获取具体的服务
     * @Param name 服务名
     **/
    public static Service getService(String name) {
        // 先从缓存中获取服务
        Service service = cache.getService(name);
        if (service != null) {
            return service;
        }
        // 根据名称找到相应服务
        InitialContext context = new InitialContext();
        service = context.lookup(name);
        // 把具体的服务放入缓存
        cache.addService(service);
        return service;
    }
}
