package com.design;

import java.util.Date;

public class Lesson7 {
    public static void main(String[] args) throws Exception{
//        Date date = new Date();
//        Human p1 = new Human(23, date);
//        Human p2 = p1;
//        System.out.println(p1);
//        System.out.println(p2);

        Date date = new Date();

        Human p1 = new Human(23, date);
        Human p2 = (Human) p1.clone();
        System.out.println(p1);
        System.out.println(p2);

//        Date date = new Date();
//
//        Human p1 = new Human(23, date);
//        Human p2 = (Human) p1.clone();
//        System.out.println(p1); // com.isoft.Human@1540e19d
//        System.out.println(p2); // com.isoft.Human@677327b6
//
//        System.out.println(p1.getAge() == p2.getAge()); // true
//        date.setTime(234234234L);
//        System.out.println(p1.getBirth()); // Sun Jan 04 01:03:54 CST 1970
//        System.out.println(p2.getBirth()); // Sun Jan 04 01:03:54 CST 1970
//        System.out.println(p1.getBirth() == p2.getBirth()); // true
    }
}

//class Human implements Cloneable{
//    private int age; // 定义年龄字段
//    private Date birth; // 定义生日字段
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    public Human(int age, Date birth) {
//        this.age = age;
//        this.birth = birth;
//    }
//    public Human() {
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Date getBirth() {
//        return birth;
//    }
//
//    public void setBirth(Date birth) {
//        this.birth = birth;
//    }
//}

class Human implements Cloneable{

    private int age; // 定义年龄字段
    private Date birth; // 定义生日字段

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Human p = (Human) super.clone();
        p.birth = (Date) birth.clone();
        return p;
    }

    public Human(int age, Date birth) {
        this.age = age;
        this.birth = birth;
    }

    public Human() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}