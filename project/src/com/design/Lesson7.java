package com.design;

import java.util.Date;

public class Lesson7 {
    public static void main(String[] args) throws Exception{
//        Date date = new Date();
//        Person p1 = new Person(23, date);
//        Person p2 = p1;
//        System.out.println(p1);
//        System.out.println(p2);

        Date date = new Date();

        Person p1 = new Person(23, date);
        Person p2 = (Person) p1.clone();
        System.out.println(p1);
        System.out.println(p2);

//        Date date = new Date();
//
//        Person p1 = new Person(23, date);
//        Person p2 = (Person) p1.clone();
//        System.out.println(p1); // com.isoft.Person@1540e19d
//        System.out.println(p2); // com.isoft.Person@677327b6
//
//        System.out.println(p1.getAge() == p2.getAge()); // true
//        date.setTime(234234234L);
//        System.out.println(p1.getBirth()); // Sun Jan 04 01:03:54 CST 1970
//        System.out.println(p2.getBirth()); // Sun Jan 04 01:03:54 CST 1970
//        System.out.println(p1.getBirth() == p2.getBirth()); // true
    }
}

//class Person implements Cloneable{
//    private int age; // 定义年龄字段
//    private Date birth; // 定义生日字段
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    public Person(int age, Date birth) {
//        this.age = age;
//        this.birth = birth;
//    }
//    public Person() {
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

class Person implements Cloneable{

    private int age; // 定义年龄字段
    private Date birth; // 定义生日字段

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.birth = (Date) birth.clone();
        return p;
    }

    public Person(int age, Date birth) {
        this.age = age;
        this.birth = birth;
    }

    public Person() {
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