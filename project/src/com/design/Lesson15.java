package com.design;

public class Lesson15 {
    public static void main(String[] args) {
//        String name = "caiya";
//
//        IBuyer buyer = new Buyer(name);
//        // 登录
//        buyer.login(name, "123456");
//        // 开始购票
//        buyer.bugTicket();

        // 使用代理类完成购票操作
        String name = "caiya";
        IBuyer buyer = new Buyer(name);
        IBuyer proxyBuyer = new BuyerProxy(buyer);
        proxyBuyer.login(name, "123456");
        proxyBuyer.bugTicket();
    }
}
/**
 * @Desc 购票统一接口
 * @Author chaozhou
 */
interface IBuyer {

    /**
     * 购票登录接口
     * @param username 用户名
     * @param password 密码
     */
    void login(String username, String password);

    /**
     * 模拟购票接口
     */
    void bugTicket();
}
/**
 * @Desc 购票接口的具体实现类
 * @Author chaozhou
 */
class Buyer implements IBuyer{

    private String name; // 模拟当前购票人

    public Buyer(String name) {
        this.name = name;
    }

    @Override
    public void login(String username, String password) {
        System.out.println("用户：" + username + " 使用密码：" + password + " 已登录成功");
    }

    @Override
    public void bugTicket() {
        System.out.println(name + " 正在购票");
    }
}
/**
 * @Desc 代理类
 * @Author chaozhou
 */
class BuyerProxy implements IBuyer{

    private IBuyer buyer;

    public BuyerProxy(IBuyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void login(String username, String password) {
        this.buyer.login(username, password);
    }

    @Override
    public void bugTicket() {
        before();
        this.buyer.bugTicket();
        after();
    }

    private void before() {
        System.out.println("准备定时任务，开始刷票");
    }

    private void after() {
        System.out.println("刷票成功，短信通知客户");
    }

}