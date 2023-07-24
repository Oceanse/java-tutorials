package com.demo.lambda.method_reference;

import com.demo.basic.code_block.construct_block.demo1.Person;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，或者说已经有现成的方法实现了lambda体， 我们就可以使用“方法引用”，
 * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！因为我们要使用现成方法实现抽象方法，所以两者之间的参数列表和返回值需要完全相同
 * 2、若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * 使用方法引用就是用一个方法最简洁的形式实现另一个抽象方法，然后参数和箭头都能省略，语法更加的简洁，但是同时也降低了代码可读性
 * <p>
 * <p>
 * 语法格式：
 * 实例方法引用： 对象::实例方法名,  eg：System.out::println
 * 静态方法引用： 类::静态方法名   eg: Person::getAge
 * 类::实例方法名, lambda参数列表的第一个参数是方法调用者类型，第二个参数是实例方法参数类型，可以使用（类名::实例方法名）
 * 构造方法引用: ClassName::new或者ClassName[]::new  需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 */
public class MethodRef {

    @Test
    public void instanceMethodReference() {
        //lambda体只有一条语句，花括号和分号可以省略
        //lambda体的功能已经有现成的实例方法：PrintStream对象的 void println(String x) 实现，这里要实现的抽象方法是void accept(String t), 两者的函数列表和返回值类型保持一致，所以可以使用实例方法引用： 对象::实例方法名
        Consumer<String> consumer = item -> System.out.println(item);

        //使用方法引用后参数和箭头都能省略，更加简洁，但是同时也降低了代码可读性
        Consumer<String> consumer2 = System.out::println;

        consumer.accept("ocean");
        consumer2.accept("health");
    }


    @Test
    public void instanceMethodReference2() {

        Person person = new Person("ocean", 32);
        Supplier<String> supplier = () -> person.getName();
        Supplier<Integer> supplier2 = () -> person.getAge();

        //lambda体的功能已经有现成的实例方法 String getName() 实现，这里要实现的抽象方法是String get(), 两者的函数列表和返回值类型保持一致，所以可以使用实例方法引用： 对象::实例方法名
        //使用方法引用后参数和箭头都能省略，更加简洁，但是同时也降低了代码可读性
        Supplier<String> supplier3 = person::getName;
        String name = supplier3.get();
        System.out.println(name);

        //lambda体的功能已经有现成的实例方法 String getAge() 实现，这里要实现的抽象方法是String get(), 两者的函数列表和返回值类型保持一致，所以可以使用实例方法引用： 对象::实例方法名
        //使用方法引用后参数和箭头都能省略，更加简洁，但是同时也降低了代码可读性
        Supplier<Integer> supplier4 = person::getAge;
        Integer age = supplier4.get();
        System.out.println(age);
    }


    @Test
    public void staticMethodReference() {
        //lambda体的功能已经有现成的静态方法static int compare(int x, int y) 实现，这里要实现的抽象方法是int compare(int x, int y), 两者的函数列表和返回值类型保持一致，所以可以使用静态方法引用：  类::静态方法名
        Comparator<Integer> comparator = (number1, number2) -> Integer.compare(number1, number2);

        //使用方法引用后参数和箭头都能省略，更加简洁，但是同时也降低了代码可读性
        Comparator<Integer> comparator2 = Integer::compare;
    }


    /**
     * https://blog.csdn.net/cn19870125/article/details/107692658
     * 使用条件的两种说法：
     * 1 lambda参数列表（接口方法参数列表）的第一个参数是引用方法调用者类型，第二个参数是实例方法参数类型，可以使用（调用者类名::实例方法名）
     * 2 接口方法的参数比引用方法的参数多一个， 接口方法的第一个参数恰巧是调用引用方法的对象（其引用方法所在类或其父类的实例）
     */
    @Test
    public void specialInstanceMethodReference2() {
        //使用类名::实例方法名调用实例方法
        //lambda参数列表的第一个参数是方法调用者类型，第二个参数是实例方法参数类型，可以使用（类名::实例方法名）
        BiPredicate<String, String> predicate = (str1, str2) -> str1.equals(str2);

        BiPredicate<String, String> predicate2 = String::equals;

        boolean test = predicate.test("ocean", "ocean");
        boolean test2 = predicate2.test("ocean", "ocean");
        System.out.println(test);
        System.out.println(test2);

    }


    /**
     * 空参构造方法引用
     * 接口和构造器的参数列表、返回值要一致
     */
    @Test
    public void constructorMethodReference() {
        //lambda体的功能已经有现成的构造方法实现new Person()，这里要实现的抽象方法是Person get(), 两者的函数列表和返回值类型保持一致，所以可以使用构造方法引用：  ClassName::new
        Supplier<Person> supplier = () -> new Person();
        //这里调用的是空参构造方法
        Supplier<Person> supplier2 = Person::new;

        Person person = supplier.get();
        Person person1 = supplier2.get();
    }

    /**
     * 单参构造方法引用
     * 接口和构造器的参数列表、返回值要一致
     */
    @Test
    public void constructorMethodReference2() {
        //lambda体的功能已经有现成的构造方法new Person(int age) 实现，这里要实现的抽象方法是Person apply(Integer i), 两者的函数列表和返回值类型保持一致，所以可以使用构造方法引用：  ClassName::new
        Function<Integer, Person> function = age -> new Person(age);
        //这里调用的是person(int age)构造方法
        Function<Integer, Person> function2 = Person::new;

        Person person = function.apply(32);
        Person person1 = function.apply(33);
        System.out.println(person.getAge());
        System.out.println(person1.getAge());
    }


    /**
     * 双参构造方法引用
     * 接口和构造器的参数列表、返回值要一致
     */
    @Test
    public void constructorMethodReference3() {
        //lambda体的功能已经有现成的构造方法new Person(String name, int age) 实现，这里要实现的抽象方法是Person apply(String name, Integer age), 两者的函数列表和返回值类型保持一致，所以可以使用构造方法引用：  ClassName::new
        BiFunction<String, Integer, Person> function = (name, age) -> new Person(name, age);
        //这里调用的是Person(String name, int age)构造方法
        BiFunction<String, Integer, Person> function2 = Person::new;

        Person person = function.apply("ocean", 32);
        Person person1 = function.apply("ocean", 33);
        System.out.println(person.getAge());
        System.out.println(person1.getAge());
    }


    /**
     * 数组构造方法引用
     * 接口和构造器的参数列表、返回值要一致
     */
    @Test
    public void constructorMethodReference4() {
        //数组构造方法
        //lambda体的功能已经有现成的构造方法new String[int number] 实现，这里要实现的抽象方法是String[] apply(Integer i), 两者的函数列表和返回值类型保持一致，所以可以使用构造方法引用：  ClassName[]::new
        Function<Integer, String[]> function = num -> new String[10];
        Function<Integer, String[]> function2 = String[]::new;

        String[] arr1 = function.apply(10);
        String[] arr2 = function.apply(20);
        System.out.println(arr1.length);
        System.out.println(arr2.length);
    }

}
