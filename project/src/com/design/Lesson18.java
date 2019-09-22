package com.design;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Lesson18 {
    public static void main(String[] args) throws IOException {
        AbstractExpression left = new TerminalExpression(12);
        AbstractExpression right = new TerminalExpression(34);
        AbstractExpression calExpression = new NonTerminalExpression(left, right);
        Context context = new Context("+");
        Integer result = calExpression.interpreter(context);
        System.out.println(result);
//        String expStr = getExpStr();
//        //赋值
//        HashMap<String, Integer> var = getValue(expStr);
//        Calculator cal = new Calculator(expStr);
//        System.out.println("运算结果为：" + expStr + "=" + cal.run(var));
    }

//    //获得表达式
//    static String getExpStr() throws IOException {
//        System.out.print("请输入表达式：");
//        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
//    }
//
//    //获得值映射
//    static HashMap<String, Integer> getValue(String expStr) throws IOException {
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        //解析有几个参数要传递
//        for (char ch : expStr.toCharArray()) {
//            if (ch != '+' && ch != '-') {
//                //解决重复参数的问题
//                if (!map.containsKey(String.valueOf(ch))) {
//                    System.out.print("请输入参数" + ch + "的值：");
//                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
//                    map.put(String.valueOf(ch), Integer.valueOf(in));
//                }
//            }
//        }
//        return map;
//    }
}
//
//// 抽象类
//abstract class Expression {
//    // 解析公式和数值，其中var中的key是公式中具体的参数如：a,b,c，value为运算时取得的具体数字
//    abstract int interpreter(HashMap<String, Integer> var);
//}
//
//// 终结符
//class VarExpression extends Expression {
//    private String key;
//
//    VarExpression(String key) {
//        this.key = key;
//    }
//
//    // 从map获取
//    @Override
//    int interpreter(HashMap<String, Integer> var) {
//        return var.get(this.key);
//    }
//}
//
//// 非终结符
//abstract class SymbolExpression extends Expression {
//    Expression left;
//    Expression right;
//
//    SymbolExpression(Expression left, Expression right) {
//        this.left = left;
//        this.right = right;
//    }
//}
//
//// 加法解析器
//class AddExpression extends SymbolExpression {
//    AddExpression(Expression left, Expression right) {
//        super(left, right);
//    }
//
//    @Override
//    int interpreter(HashMap<String, Integer> var) {
//        return this.left.interpreter(var) + this.right.interpreter(var);
//    }
//}
//
//// 减法解析器
//class SubExpression extends SymbolExpression {
//    SubExpression(Expression left, Expression right) {
//        super(left, right);
//    }
//
//    @Override
//    int interpreter(HashMap<String, Integer> var) {
//        return this.left.interpreter(var) - this.right.interpreter(var);
//    }
//}
//
//// 封装类
//class Calculator {
//    // 定义一个表达式
//    private Expression expression;
//
//    // 构造函数传参、解析
//    Calculator(String expStr) {
//        // 定义一个栈，安排运算的先后顺序
//        Stack<Expression> stack = new Stack<Expression>();
//        // 表达式拆分为字符数组
//        char[] charArray = expStr.toCharArray();
//        // 运算
//        Expression left = null;
//        Expression right = null;
//        for (int i = 0; i < charArray.length; i++) {
//            switch (charArray[i]) {
//                case '+': // 加法结果放入栈中
//                    left = stack.pop();
//                    right = new VarExpression(String.valueOf(charArray[++i]));
//                    stack.push(new AddExpression(left, right));
//                    break;
//                case '-':
//                    left = stack.pop();
//                    right = new VarExpression(String.valueOf(charArray[++i]));
//                    stack.push(new SubExpression(left, right));
//                    break;
//                default:
//                    stack.push(new VarExpression(String.valueOf(charArray[i])));
//            }
//        }
//        this.expression = stack.pop();
//    }
//
//    // 开始运算
//    int run(HashMap<String, Integer> var) {
//        return this.expression.interpreter(var);
//    }
//}

// 上下文类，这里只是简单说明下，实际的context可没这么简单
class Context {
    private String symbol = "";
    Context(String symbol) {
        this.symbol = symbol;
    }
    String getSymbol() {
        return symbol;
    }
}
// 抽象解释器
abstract class AbstractExpression {
    // 解释器接口
    abstract int interpreter(Context context);
}
// 终结符，即我们的参数构造类
class TerminalExpression extends AbstractExpression{
    private Integer arg;
    public TerminalExpression(Integer arg) {
        this.arg = arg;
    }
    @Override
    int interpreter(Context context) {
        return this.arg;
    }
}
// 非终结符，即我们的运算符构造类
class NonTerminalExpression extends AbstractExpression {
    // 代表运算符两侧的参数，即a、b
    private AbstractExpression left;
    private AbstractExpression right;
    NonTerminalExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }
    @Override
    int interpreter(Context context) {
        // 实现具体的 a +b 的解释执行操作
        if (!context.getSymbol().equalsIgnoreCase("")) {
            return this.left.interpreter(context) + this.right.interpreter(context);
        }
        return 0;
    }
}