package com.design;

public class Lesson13 {
    public static void main(String[] args) {
        FireSystem fireSystem = new FireSystem();
        UserSystem userSystem = new UserSystem();

        // 不引入外观模式
        fireSystem.useBullet(); // 上子弹
        fireSystem.fire(); // 开火
        userSystem.loseBlood(); // 掉血
        userSystem.addScore(); // 加分

        // 引入外观模式
        Facade facade = new Facade(fireSystem, userSystem);
        facade.shooting(); // 射击
    }
}
/**
 * @Desc 武器子系统
 * @Author chaozhou
 */
class FireSystem {

    public void fire() {
        System.out.println("开火....");
    }

    public void useBullet() {
        System.out.println("上子弹....");
    }

}

/**
 * @Desc 用户子系统
 * @Author chaozhou
 */
class UserSystem {

    public void loseBlood() {
        System.out.println("掉血...");
    }

    public void addScore() {
        System.out.println("得分...");
    }
}

/**
 * @Desc Facade 角色
 * @Author chaozhou
 */
class Facade {

    // 被委托的对象
    private FireSystem fireSystem;
    private UserSystem userSystem;

    public Facade(FireSystem fireSystem, UserSystem userSystem) {
        this.fireSystem = fireSystem;
        this.userSystem = userSystem;
    }

    // 模拟射击的门面接口 API
    public void shooting() {
        fireSystem.useBullet(); // 上子弹
        fireSystem.fire(); // 开火
        userSystem.loseBlood(); // 敌人掉血
        userSystem.addScore(); // 自己加分
    }
}