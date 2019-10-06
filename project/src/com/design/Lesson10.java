package com.design;

import java.util.ArrayList;
import java.util.List;

public class Lesson10 {
    public static void main(String[] args) {
        // 原始数据集合
        List<Rubbish> rubbishList = new ArrayList<>();
        rubbishList.add(new Rubbish("果壳", false, false, true, false));
        rubbishList.add(new Rubbish("陶瓷", false, false, true, false));
        rubbishList.add(new Rubbish("菜根菜叶", false, false, false, true));
        rubbishList.add(new Rubbish("果皮", false, false, false, true));
        rubbishList.add(new Rubbish("水银温度计", true, false, false, false));
        rubbishList.add(new Rubbish("电池", true, false, false, false));
        rubbishList.add(new Rubbish("灯泡", true, false, false, false));
        rubbishList.add(new Rubbish("废纸塑料", false, true, false, false));
        rubbishList.add(new Rubbish("金属和布料", false, true, false, false));
        rubbishList.add(new Rubbish("玻璃", false, true, false, false));

        // 四种不同的过滤标准
        Criteria dryRubbishCriteria = new DryRubbishCriteria();
        Criteria wetRubbishCriteria = new WetRubbishCriteria();
        Criteria harmfulRubbishCriteria = new HarmfulRubbishCriteria();
        Criteria recycledRubbishCriteria = new RecycledRubbishCriteria();

        System.out.println("干垃圾: ");
        printRubbishes(dryRubbishCriteria.rubbishFilter(rubbishList));

        System.out.println("湿垃圾: ");
        printRubbishes(wetRubbishCriteria.rubbishFilter(rubbishList));

        System.out.println("有害垃圾: ");
        printRubbishes(harmfulRubbishCriteria.rubbishFilter(rubbishList));

        System.out.println("可回收垃圾: ");
        printRubbishes(recycledRubbishCriteria.rubbishFilter(rubbishList));
    }

    private static void printRubbishes(List<Rubbish> rubbishes) {
        for (Rubbish rubbish : rubbishes) {
            System.out.println(rubbish);
        }
    }
}

// “垃圾”类，也就是我们的被过滤的主体角色
class Rubbish {
    private String name; // 垃圾名称
    private boolean isHarm; // 是否有害垃圾
    private boolean isRecycled; // 是否可回收
    private boolean isDry; // 是否干垃圾
    private boolean isWet; // 是否湿垃圾

    public Rubbish(String name, boolean isHarm, boolean isRecycled, boolean isDry, boolean isWet) {
        this.name = name;
        this.isHarm = isHarm;
        this.isRecycled = isRecycled;
        this.isDry = isDry;
        this.isWet = isWet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHarm() {
        return isHarm;
    }

    public void setHarm(boolean harm) {
        isHarm = harm;
    }

    public boolean isRecycled() {
        return isRecycled;
    }

    public void setRecycled(boolean recycled) {
        isRecycled = recycled;
    }

    public boolean isDry() {
        return isDry;
    }

    public void setDry(boolean dry) {
        isDry = dry;
    }

    public boolean isWet() {
        return isWet;
    }

    public void setWet(boolean wet) {
        isWet = wet;
    }

    @Override
    public String toString() {
        return "Rubbish{" +
                "name='" + name + '\'' +
                ", isHarm=" + isHarm +
                ", isRecycled=" + isRecycled +
                ", isDry=" + isDry +
                ", isWet=" + isWet +
                '}';
    }
}

// 抽象过滤器角色
interface Criteria {
    // 定义过滤的标准
    List<Rubbish> rubbishFilter(List<Rubbish> rubbishes);
}

// 干垃圾
class DryRubbishCriteria implements Criteria {

    @Override
    public List<Rubbish> rubbishFilter(List<Rubbish> rubbishes) {
        List<Rubbish> rubbishList = new ArrayList<>();
        for (Rubbish rubbish : rubbishes) {
            // 这里只过滤出所有干垃圾
            if (rubbish.isDry()) {
                rubbishList.add(rubbish);
            }
        }
        return rubbishList;
    }
}

// 有害垃圾
class HarmfulRubbishCriteria implements Criteria {
    @Override
    public List<Rubbish> rubbishFilter(List<Rubbish> rubbishes) {
        List<Rubbish> rubbishList = new ArrayList<>();
        for (Rubbish rubbish : rubbishes) {
            // 这里只过滤出所有有害垃圾
            if (rubbish.isHarm()) {
                rubbishList.add(rubbish);
            }
        }
        return rubbishList;
    }
}

// 可回收垃圾
class RecycledRubbishCriteria implements Criteria {
    @Override
    public List<Rubbish> rubbishFilter(List<Rubbish> rubbishes) {
        List<Rubbish> rubbishList = new ArrayList<>();
        for (Rubbish rubbish : rubbishes) {
            // 这里只过滤出所有可回收垃圾
            if (rubbish.isRecycled()) {
                rubbishList.add(rubbish);
            }
        }
        return rubbishList;
    }
}

// 湿垃圾
class WetRubbishCriteria implements Criteria {
    @Override
    public List<Rubbish> rubbishFilter(List<Rubbish> rubbishes) {
        List<Rubbish> rubbishList = new ArrayList<>();
        for (Rubbish rubbish : rubbishes) {
            // 这里只过滤出所有湿垃圾
            if (rubbish.isWet()) {
                rubbishList.add(rubbish);
            }
        }
        return rubbishList;
    }
}