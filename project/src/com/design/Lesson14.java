package com.design;

import java.util.HashMap;

public class Lesson14 {
    public static void main(String[] args) {
        // 假设有2个科目，初始化一遍池子
        for (int i = 0; i < 2; i++) {
            String subject = "科目" + i;
            ExamInfoFactory.getExamInfo(subject);
        }
        // 假设3个考生考试
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                ExamInfo examInfo = ExamInfoFactory.getExamInfo("科目" + j);
                examInfo.setUser("考生" + i);
                System.out.println(examInfo);
            }
        }
    }
}
/**
 * @Desc 考生的考试信息
 * @Author chaozhou
 */
class ExamInfo {

    // 内部状态，用于在各个对象之间共享，不随环境改变而改变，存储在享元对象内部，往往作为对象的动态附加信息存在
    private String user; // 考生

    // 外部状态，随环境改变而改变，属于不可共享的状态，是对象得以依赖的一个标记
    private String subject; // 考试科目

    public ExamInfo(String subject) {
        this.subject = subject;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
                "user='" + user + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

/**
 * @Desc 工厂模式
 * @Author chaozhou
 */
class ExamInfoFactory {

    // 对象池，用来支持细粒度对象的复用
    private static HashMap<String, ExamInfo> pool = new HashMap<>();

    public static ExamInfo getExamInfo(String subject) {
        // 设置返回的对象
        ExamInfo examInfo = null;
        if (!pool.containsKey(subject)) {
            System.out.println("建立对象，并放到池中..." + subject);
            examInfo = new ExamInfo(subject);
            pool.put(subject, examInfo);
        } else {
            examInfo = pool.get(subject);
            System.out.println("直接从池中获取..." + subject);
        }
        return examInfo;
    }
}