package com.demo.lambda.lambda_intro.demo1;


import com.demo.basic.code_block.construct_block.demo1.Person;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.testng.annotations.Test;

/**
 * 本质：用来创建函数型接口对象
 * <p>
 * 使用条件：
 * 1 使用Lambda只能创建函数型接口(内部只有一个抽象方法的接口)对象
 * 2 使用Lambda必须具有上下文推断。 也就是方法的参数或局部变量类型必须为Lambda对应的接口类型，才能使用Lambda创建该接口的实例。
 * <p>
 * 作用：lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值
 * <p>
 * 语法： (参数列表)->{方法体}，参数列表就是函数型接口内部的抽象方法的参数列表，方法体就是对这个抽象方法的实现。所以要把lambda和抽象类、抽象方法的参数和实现关联起来
 * 参数：
 * ->是Lambda运算符，其左边部分是输入参数,这个参数就是函数型接口中被实现的方法的参数列表，数量可以为0、1或者多个;
 * 只有当输入参数为1时，Lambda表达式左边的一对小括弧才可以省略;
 * 输入参数的数量大于或者等于2时，Lambda表达式左边的一对小括弧中的多个参数质检使用逗号分割:（a,b）->{}。
 * 方法体：
 * ->右边是方法体,方法体就是函数型接口被实现的抽象方法的方法体
 * 如果大括号内有且仅有一个语句且需要返回值，那么可以同时省略大括号、return关键字及语句分号；eg: ICar car=()->10
 * 或者完整写法：ICar car=()-> {return 10;};
 * <p>
 * 缺点：
 * 使用Lambda表达式会减弱代码的可读性，而且Lambda表达式的使用局限性比较强，只能适用于接口只有一个抽象方法时使用
 * <p>
 * <p>
 * <p>
 * 双冒号：
 * 英文：double colon，双冒号（::）运算符在Java 8中被用作方法引用（method reference），方法引用是与lambda表达式相关的一个重要特性。
 * 它提供了一种不执行方法的方法。为此，方法引用需要由兼容的函数接口组成的目标类型上下文。
 * 通俗解释： 就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下
 * <p>
 * Method References
 * You use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing
 * but call an existing method. In those cases, it’s often clearer to refer to the existing method by name.
 * Method references enable you to do this; they are compact, easy-to-read lambda expressions for methods that
 * already have a name.(摘自oracle官网)
 * 大致意思是，使用lambda表达式会创建匿名方法， 但有时候需要使用一个lambda表达式只调用一个已经存在的方法（不做其它）， 所以这才有了方法引用！
 * <p>
 * 以下是Java 8中方法引用的一些语法：
 * 静态方法引用（static method）语法：classname::methodname 例如：Person::getAge
 * 对象的实例方法引用语法：instancename::methodname 例如：System.out::println
 * 对象的超类方法引用语法： super::methodname
 * 类构造器引用语法： classname::new 例如：ArrayList::new
 * 数组构造器引用语法： typename[]::new 例如： String[]:new
 */
public class Action implements Runnable {
    @Override
    public void run() {
        System.out.println("fly....");
    }
}


class LambdaTest {

    public static void main(String[] args) {

        //传统方法,需要创建实现类及其对象
        Runnable r = new Action();
        new Thread(r).start();


        //匿名内部类，不需要创建实现类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run....");
            }
        }).start();


        //lambda表达式更加简洁
        new Thread(() -> {
            System.out.println("walk...");
        }).start();
    }


    /**
     * 要把lambda和抽象类、抽象方法的参数和实现关联起来
     * Consumer,Supplier, Predicate,Function都是函数式接口
     */
    @Test
    public void test() {
        //lambda创建对象的过程可以想象成包含了创建了实现类及其对象
        Consumer<String> consumer = item -> {
            System.out.println(item.length());
        };
        consumer.accept("apple pro14");


        //lambda创建对象的过程可以想象成包含了创建了实现类及其对象
        Supplier<Person> supplier = () -> new Person();
        Person person = supplier.get();
        System.out.println(person);

        //lambda创建对象的过程可以想象成包含了创建了实现类及其对象
        Predicate<Integer> predicate = item -> item % 2 == 0;
        boolean test = predicate.test(6);
        System.out.println(test);

        //lambda创建对象的过程可以想象成包含了创建了实现类及其对象
        Function<String,Integer> function=item->item.length();
        Integer ocean = function.apply("ocean");
        System.out.println(ocean);
    }


}