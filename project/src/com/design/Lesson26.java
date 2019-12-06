package com.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lesson26 {
    public static void main(String[] args) {
        Computor computor = new Computor();
        computor.showFileAndDict(new NormalVisitor(Collections.singletonList("look-dictionary")));
        computor.showFileAndDict(new RootVisitor());
        computor.showFileAndDict(new NormalVisitor(Arrays.asList("look-file", "look-dictionary")));
    }
}

// 被访问的元素
interface Element {
    void accept(Visitor visitor);
}
// 文件元素
class FileElement implements Element {
    // 当前元素的访问权限
    private String lookPerms;
    // 文件名
    private String name;
    FileElement(String name, String lookPerms) {
        this.name = name;
        this.lookPerms = lookPerms;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    String getName() {
        return name;
    }
    String getLookPerms() {
        return lookPerms;
    }
}
// 文件夹
class DictionaryElement implements Element {
    // 当前元素的访问权限
    private String lookPerms;
    // 文件夹名
    private String name;
    DictionaryElement(String name, String lookPerms) {
        this.name = name;
        this.lookPerms = lookPerms;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    String getName() {
        return name;
    }
    String getLookPerms() {
        return lookPerms;
    }
}
// 访问者
interface Visitor {
    // 定义对不同的元素（文件）进行访问时的具体行为
    void visit(FileElement fileElement);
    // 定义对不同的元素（文件夹）进行访问时的具体行为
    void visit(DictionaryElement dictionaryElement);
}
// root用户
class RootVisitor implements Visitor {
    @Override
    public void visit(FileElement fileElement) {
        System.out.println("当前：Root " + "要访问的文件名：" + fileElement.getName() + " 允许访问!");
    }
    @Override
    public void visit(DictionaryElement dictionaryElement) {
        System.out.println("当前：Root " + "要访问的文件夹名：" + dictionaryElement.getName() + " 允许访问!");
    }
}
// 普通用户
class NormalVisitor implements Visitor {
    // 定义该用户具备的权限集合
    private List<String> lookPerms;
    NormalVisitor(List<String> lookPerms) {
        this.lookPerms = lookPerms;
    }
    @Override
    public void visit(FileElement fileElement) {
        if (this.lookPerms.indexOf(fileElement.getLookPerms()) >= 0) {
            System.out.println("当前：普通用户" + " 具备权限：" + this.lookPerms.toString() + " 要访问的文件名：" + fileElement.getName()  + " 允许访问!");
        } else {
            System.out.println("当前：普通用户" + " 具备权限：" + this.lookPerms.toString() +" 要访问的文件名：" + fileElement.getName() + " 权限不足，禁止访问!");
        }
    }
    @Override
    public void visit(DictionaryElement dictionaryElement) {
        if (this.lookPerms.indexOf(dictionaryElement.getLookPerms()) >= 0) {
            System.out.println("当前：普通用户" + " 具备权限：" + this.lookPerms.toString() + " 要访问的文件夹名：" + dictionaryElement.getName()  + " 允许访问!");
        } else {
            System.out.println("当前：普通用户" + " 具备权限：" + this.lookPerms.toString() +" 要访问的文件夹名：" + dictionaryElement.getName() + " 权限不足，禁止访问!");
        }
    }
}
// ObjectStructure角色
class Computor {
    // 计算机中的文件和文件夹List
    private List<Element> elementList = new LinkedList<>();
    {
        elementList.add(new FileElement("Java讲义.pdf", "look-file"));
        elementList.add(new DictionaryElement("program", "look-dictionary"));
    }
    // 展示该电脑中的文件和文件夹
    void showFileAndDict(Visitor visitor) {
        for (Element element: elementList) {
            element.accept(visitor);
        }
    }
}