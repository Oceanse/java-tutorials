package com.demo.basic.keywords.statics.static_import;


import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.System.out;
import static com.demo.basic.keywords.statics.static_import.Stu.getScores;


/**
 * 作用：省略类名直接访问静态成员，包括静态变量和静态方法
 * 使用静态导入可以使被导入类的静态变量和静态方法在当前类直接可见，使用这些静态成员无需再给出他们的类名
 * import static和import有着异曲同工之妙，本质都是为了简化程序员的工作量，import可以省略包名去访问不同包的类，而import static类名都可以省略
 *
 * 格式：
 * 1 import static 包名.类名.静态方法名;
 * 2 import static 包名.类名.静态属性名;
 * 3 import static 包名.类名.*;
 *
 * 注意：
 * 过度地使用静态导入会在一定程度上降低代码的可读性。
 *
 * @author epanhai
 */
public class StaticImport {

    public static void main(String[] args) {

        // 省略类名直接调用类变量
        out.println(" public final static PrintStream out");
        out.println(PI);

        // 省略类名直接调用类方法
        out.println(getScores("Chinese"));
        out.println(sqrt(256));
    }

}


class Stu{
    static Map<String,Double> scores= new HashMap();

    public Stu(String subject,double value) {
        scores.put(subject,value);
    }

    public static double getScores(String subject) {
        return scores.get(subject);
    }
}
