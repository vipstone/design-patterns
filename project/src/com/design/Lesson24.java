package com.design;

import java.util.*;
import java.util.Iterator;

public class Lesson24 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(3);
        list.add("admin");
        list.add("code-shop");
        list.add("lucy");

        StringSortContext context = new StringSortContext(new StringSortStrategyReverse());
        List<String> reverseSortedList = context.getSortList(list);
        System.out.println(reverseSortedList); // [lucy, code-shop, admin]

        StringSortContext context2 = new StringSortContext(new StringSortStrategyNormal());
        List<String> normalSortedList = context2.getSortList(list);
        System.out.println(normalSortedList); // [admin, code-shop, lucy]
//        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                // 使用自然排序的逆序排列
//                return o2.compareTo(o1);
//            }
//        });
//        treeSet.add("lily");
//        treeSet.add("yerkim");
//        treeSet.add("admin");
//
//        for (String s : treeSet) {
//            System.out.println(s);
//        }
    }
}

//// 抽象策略角色
//interface Strategy {
//    void algorithmInterface();
//}
//// 具体策略角色1
//class ConcreteStrategy1 implements Strategy {
//    @Override
//    public void algorithmInterface() {
//        System.out.println("具体策略1");
//    }
//}
//// 具体策略角色2
//class ConcreteStrategy2 implements Strategy {
//    @Override
//    public void algorithmInterface() {
//        System.out.println("具体策略2");
//    }
//}
//// 上下文角色
//class Context {
//    private Strategy strategy = null;
//
//    public Context(Strategy strategy) {
//        this.strategy = strategy;
//    }
//
//    // 对外接口
//    public void contextInterface() {
//        this.strategy.algorithmInterface();
//    }
//}

// 字符串的抽象排序策略
interface IStringSortStrategy {
    List<String> sort(List<String> list);
}
// 排序策略——正序
class StringSortStrategyNormal implements IStringSortStrategy{
    @Override
    public List<String> sort(List<String> list) {
        Collections.sort(list);
        return list;
    }
}
// 排序策略——倒序
class StringSortStrategyReverse implements IStringSortStrategy{
    @Override
    public List<String> sort(List<String> list) {
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }
}
// 上下文角色
class StringSortContext {
    private IStringSortStrategy strategy;

    public StringSortContext(IStringSortStrategy strategy) {
        this.strategy = strategy;
    }

    // 获取排序结果
    public List<String> getSortList(List<String> list) {
        return this.strategy.sort(list);
    }
}