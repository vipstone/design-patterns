package com.design;

import java.util.Arrays;
import java.util.List;

public class Lesson2 {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        GroupLeader groupLeader = new GroupLeader(Arrays.asList(new Girl(), new Girl()));
        teacher.command(groupLeader);
    }
}

// girl类
class Girl {

}
// 组长类
class GroupLeader {

    private final List<Girl> girls;

    public GroupLeader(List<Girl> girls) {
        this.girls = girls;
    }
    // 清点 girl 人数
    public void countGirls() {
        System.out.println("The sum of girls is " + girls.size());
    }
}
// 教师类
class Teacher {
    // 命令模式
    public void command(GroupLeader leader){
        leader.countGirls();
    }
}