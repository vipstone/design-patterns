package com.design;

public class Lesson3 {
    public static void main(String[] args) {
        // 普通简单工厂测试
        SendFactory sendFactory = new SendFactory();
        Sender senderSms = sendFactory.produce("sms");
        senderSms.Send(); // 发送短信

        Sender senderEmail = sendFactory.produce("email");
        senderEmail.Send(); // 发送邮件

        Sender senderExpress = sendFactory.produce("express");
        senderExpress.Send(); // 发送快递

        // 多方法简单工厂
        Sender senderEmail2 = sendFactory.produceEmail();
        senderEmail2.Send(); // 发送邮件

        Sender senderSms2 = sendFactory.produceSms();
        senderSms2.Send(); // 发送短信

        Sender senderExpress2 = sendFactory.produceExpress();
        senderExpress2.Send(); // 发送快递

        // 静态方法简单工厂
        Sender senderEmail3 = SendFactory.produceEmailStatic();
        senderEmail3.Send(); // 发送邮件

        Sender senderSms3 = SendFactory.produceSmsStatic();
        senderSms3.Send(); // 发送短信

        Sender senderExpress3 = SendFactory.produceExpressStatic();
        senderExpress3.Send(); // 发送快递
    }
}

/********************* 普通简单工厂 start ********************/
// 工厂类接口
interface Sender {
    // 定义一个发送方法
    public void Send();
}
// 邮件发送
class EmailSender implements Sender{
    @Override
    public void Send() {
        System.out.println("发送邮件");
    }
}
// 短信发送
class SmsSender implements Sender{
    @Override
    public void Send() {
        System.out.println("发送短信");
    }
}
// 快递发送
class ExpressSender implements Sender {
    @Override
    public void Send() {
        System.out.println("发送快递");
    }
}
// “发送”工厂类
class SendFactory {
    // 普通简单工厂
    Sender produce(String type) {
        if (type == null) {
            return null;
        } else if ("email".equalsIgnoreCase(type)) {
            return new EmailSender();
        } else if ("sms".equalsIgnoreCase(type)) {
            return new SmsSender();
        } else if ("express".equalsIgnoreCase(type)) {
            return new ExpressSender();
        } else {
            return null;
        }
    }

    // 多方法简单工厂
    Sender produceSms(){
        return new SmsSender();
    }

    Sender produceEmail(){
        return new EmailSender();
    }

    Sender produceExpress() {
        return new ExpressSender();
    }

    // 静态方法简单工厂
    static Sender produceSmsStatic(){
        return new SmsSender();
    }

    static Sender produceEmailStatic(){
        return new EmailSender();
    }

    static Sender produceExpressStatic() {
        return new ExpressSender();
    }
}