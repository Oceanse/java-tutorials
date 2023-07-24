package com.demo.reflection.demo1;

import com.demo.collection_map.model.Product;
import com.demo.oop.inherit.demo4_private.Person;
import org.testng.annotations.Test;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Arrays;


/**
 * 概念1：
 * JVM装载过某个类后，会生成对应的Class对象，那么在程序运行时，就能获取这个类的全部信息，比如父接口，父类，全类名，属性，方法，构造器等，
 * 并且能动态操作这个类的属性，方法，构造器，这被称之为Java的反射机制
 * <p>
 * 概念2：：
 * 对于任意一个类， 都能够知道这个类的完整结构包括父类、父接口、属性、方法、构造器等相关信息并且能够创建对象；
 * 对于任意一个对象， 都能够调用它的任意一个方法和属性。
 * 这种动态的获取信息以及动态调用对象的方法的功能称为 java 的反射机制；
 * <p>
 * 反射机制很重要的一点就是“运行时”，其使得我们可以在程序运行时加载、探索以及使用编译期间完全未知的 .class 文件
 * <p>
 * 优点：反射增强了程序的灵活性，它也是许多框架设计的灵魂和基石
 * 缺点：反射调用方法时可以忽略权限检查，获取这个类的私有方法和属性，因此可能会破坏类的封装性而导致安全问题，另外可能会降低程序的运行效率
 */


public class ReflectionDemo {
    /**
     * 获取Class对象实例
     * .class字节码文件通过类加载器加载到内存后，在堆内存中就会创建一个Class对象，一个Class对象对应的是一个加载到JVM中的一个.class字节码文件，代表一个类型:Person.class<---->Class<Person><---->Person
     * 同一个类在运行过程中，只会被加载一次，不论通过哪种方式获得，不论创建多少个对象，都是对应着同一个Class对象
     * 不能直接创建Class的实例对象，因为Class类的构造方法是私有的，只有jvm可以去创建。
     * Class对象就像一面镜子一样，通过Class对象可以获取这个类的完整结构包括父类、父接口、属性、方法等相关信息并且能够创建对象，访问属性方法，所以形象的称之为反射
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getClassTest() throws ClassNotFoundException {
        //方式1：通过对象创建Class实例,常用于有对象实例的场景
        Class clz1 = new Apples().getClass();
        Class clz1_2 = new Apples().getClass();

        //方式2：通过类名创建Class实例，常用于参数传递场景
        Class<Apples> clz2 = Apples.class;//常用于参数传递场景

        //方式3：通过全类名创建Class实例，要求该类在classpath下，常用于读取配置文件场景
        Class<?> clz3 = Class.forName("com.demo.reflection.demo1.Apples");

        //方式4：通过系统类加载器(加载用户类路径下的类)获取Class对象，要求该类在classpath下
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clz4 = classLoader.loadClass("com.demo.reflection.demo1.Apples");

        //这里获取到的也是系统类加载器(加载用户类路径下的类)
        ClassLoader classLoader2 = this.getClass().getClassLoader();
        Class<?> clz5 = classLoader.loadClass("com.demo.reflection.demo1.Apples");

        //无论如何获取Class<Apples>对象，无论生成多少个Apples对象，Class<Apples>对象在堆内存中只有一个，对应着Apples.class文件
        System.out.println(clz1 == clz1_2 && clz1_2 == clz2 && clz2 == clz3 && clz3 == clz4 && clz4 == clz5);

        //数组也有Class对象
        Product[] products = new Product[10];
        Class productClz = products.getClass();//通过对象创建Class实例
        Class productClz2 = new Product[10].getClass();//通过对象创建Class实例
        Class<Product[]> productClz3 = Product[].class;//通过类名创建Class实例
        System.out.println(productClz == productClz2 && productClz2 == productClz3);

        //基本数据类型可通过 Class cls=基本数据类型.class 获取对应的Class对象
        Class<Double> doubleClass = double.class;  //泛型类型自动装箱
        Class<Character> characterClass = char.class;

        //Thread.State是枚举类，也有CLass对象（枚举是一种类）x
        Class<Thread.State> stateClass = Thread.State.class;

        //void也有CLass对象
        Class<Void> voidClass = void.class;

        //对于基本数据类型的包装类，还可以采用TYPE来获取对应的基本数据类型的Class实例： 包装类.TYPE
        Class<Integer> type = Integer.TYPE;
        Class<Character> type1 = Character.TYPE;
        Class<Boolean> type2 = Boolean.TYPE;

        //注解是一种接口,也有CLass对象
        Class<Override> overrideClass = Override.class;
    }


    /**
     * getClass(): Returns the runtime class of this Object.
     */
    @Test
    public void getClassTest2() {
        Object obj = new Person();
        Person person = new Person();
        System.out.println(obj.getClass().getName());
        System.out.println(person.getClass().getName());
        System.out.println(obj.getClass() == person.getClass());//两者的运行时类型一致
    }


    /**
     * 通过Class可以完整地得到一个类中的完整结构
     */
    @Test
    public void test2() {
        System.out.println("直接父接口：");
        Class<Apples> clz = Apples.class;
        Class<?>[] interfaces = clz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i].getName());
        }
        System.out.println();

        System.out.println("直接父类：");
        Class superclass = clz.getSuperclass();
        Type superclass2 = clz.getGenericSuperclass();
        System.out.println(superclass.getName());//直接父类
        System.out.println(superclass2.getTypeName());//直接父类

        System.out.println();
        System.out.println("包名：");
        Package aPackage = clz.getPackage();
        System.out.println(aPackage.getName());
    }


    /**
     * 通过反射获取构造器
     *
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {

        System.out.println("获取当前类所有public构造器: ");
        Class clz = Apples.class;
        Constructor[] constructors = clz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("\n获取当前类所有权限的构造器: ");
        Constructor[] declaredConstructors = clz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("\n获取指定的的构造器: ");
        Constructor declaredConstructor = clz.getDeclaredConstructor();
        Constructor declaredConstructor2 = clz.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor);
        System.out.println(declaredConstructor2);

        System.out.println("\n获取父类所有public构造器: ");
        Class superclass = clz.getSuperclass();
        Constructor[] constructors2 = superclass.getConstructors();
        for (Constructor constructor : constructors2) {
            System.out.println(constructor);
        }
    }


    /**
     * 通过反射获取构造器相关信息
     * 关于修饰符：method.getModifiers()方法返回访问权限修饰符常量代码，是 int 类型，例如 1 代表 public，
     * 这些数字代表的含义可以通过Modifier.toString(int)方法转换为字符串
     *
     * @throws Exception
     */
    @Test
    public void testConstructor2() throws Exception {

        Class clz = Apples.class;

        System.out.println("\n获取构造器修饰符和名字: ");
        Constructor[] declaredConstructors = clz.getDeclaredConstructors();
        for (Constructor d : declaredConstructors) {
            System.out.println(Modifier.toString(d.getModifiers()) + " " + clz.getSimpleName());
        }


        System.out.println("\n获取构造器的参数类型: ");
        Constructor declaredConstructor = clz.getDeclaredConstructor(String.class, double.class, boolean.class, String.class);
        Class[] parameterTypes = declaredConstructor.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            System.out.println(parameterType.getSimpleName());
        }
    }


    /**
     * 通过反射获取构造器并创建类对象
     *
     * @throws Exception
     */
    @Test
    public void testConstructor3() throws Exception {
        Class clz = Apples.class;

        //通过空参构造器创建对象
        Constructor constructor = clz.getConstructor();
        Apples apples = (Apples) constructor.newInstance();

        //通过指定四参构造器创建对象
        Constructor constructor1 = clz.getConstructor(String.class, double.class, boolean.class, String.class);
        Apples apples2 = (Apples) constructor1.newInstance("hongfushi", 1, true, "yantai");

        //使用泛型后构建的对象就是Apples类型
        Constructor<Apples> constructor2 = clz.getConstructor();
        Apples apples3 = constructor2.newInstance();

        //获取私有构造器要使用getDeclaredConstructor，调用时候要设置下权限
        Constructor<Apples> constructor3 = clz.getDeclaredConstructor(String.class);
        //Apples apples4 = constructor3.newInstance("apple");//运行时候会报IllegalAccessException
        constructor3.setAccessible(true);//可以通过setAccessible方法将可访问标志设为true（默认为false），会关闭访问检查。这样即使是私有的属性、方法或构造函数，也可以访问。
        Apples apples4 = constructor3.newInstance("apple");
    }


    /**
     * 通过反射创建数组
     */
    @Test
    public void testConstructor4() {
        Class<Integer> clazz = Integer.class;
        Integer[] array = (Integer[]) Array.newInstance(clazz, 10);
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 获取数组元素类型
     */
    @Test
    public void testArrayComponentType() {
        Class<?> componentType = new Apples[10].getClass().getComponentType();
        System.out.println(componentType);
        System.out.println(componentType.getName());
    }


    private static Object resizeArray(Object oldArray, int newSize) {
        //获取数组oldArray的长度
        int oldSize = Array.getLength(oldArray);
        //获取数组oldArray的元素类型
        Class elementType = oldArray.getClass().getComponentType();
        //实例一个新的数组 类型和oldArray的一样 长度参数传入的newSize
        Object newArray = Array.newInstance(elementType, newSize);
        //得到新数组newArray 和oldArray两个中长度最短的，并把长度返回给preserveLength
        int preserveLength = Math.min(oldSize, newSize);
        //数组内容复制
        if (preserveLength > 0) {
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        }
        return newArray;
    }


    /**
     * 反射获取属性
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void testField() throws NoSuchFieldException {
        //getFields()只能获取public的属性，包括从父类继承过来的public属性
        Class clz = Apples.class;
        Field[] Fields = clz.getFields();

        System.out.println("获取父子所有public属性:");
        for (Field field : Fields) {
            System.out.print(field.getName() + " ");
        }
        System.out.println();

        //获取本类所有权限的属性，不包括从父类继承过来的属性
        Field[] declaredFields = clz.getDeclaredFields();
        System.out.println("获取本类所有属性:");
        for (Field declaredField : declaredFields) {
            System.out.print(declaredField.getName() + " ");
        }

        //获取特定属性
        System.out.println("\n" + "获取特定属性:");
        Field field = clz.getField("name");
        System.out.println(field.getName());
    }


    /**
     * 反射获取属性相关信息
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void testField2() throws NoSuchFieldException {
        //getFields()只能获取public的属性，包括从父类继承过来的public属性
        Class clz = Apples.class;

        //获取本类所有权限的属性，不包括从父类继承过来的属性
        Field[] declaredFields = clz.getDeclaredFields();

        //获取属性权限修饰符和属性类型
        for (Field declaredField : declaredFields) {
            System.out.println(Modifier.toString(declaredField.getModifiers()) + " " + declaredField.getType().getSimpleName() + " " + declaredField.getName());
        }
    }


    /**
     * 反射操作属性
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void testField3() throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //getFields()只能获取public的属性，包括从父类继承过来的public属性
        Class<Apples> clz = Apples.class;
        Apples apples = clz.getConstructor().newInstance();
        //获取本类所有权限的属性，不包括从父类继承过来的属性
        Field nameField = clz.getField("name");
        nameField.set(apples, "hongfushi");
        Object name = nameField.get(apples);
        System.out.println(name);
    }


    /**
     * 反射获取方法
     *
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception {
        Class clz = Apples.class;

        //获取本类及其从父类继承过来的的public方法
        System.out.println("获取本类及其父类的public方法:");
        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + " ");
        }

        //获取本类的所有权限的方法，不包括父类
        System.out.println("\n获取本类的所有权限的方法，不包括父类:");
        Method[] declaredMethod = clz.getDeclaredMethods();
        for (Method method : declaredMethod) {
            System.out.print(method.getName() + " ");
        }

        //获取特定方法
        System.out.println("\n获取指定方法:");
        Method setArea = clz.getMethod("setArea", String.class);
        System.out.println(setArea.getName());
    }


    /**
     * 反射获取方法修饰符、返回值、方法名、方法注解等相关信息
     * 关于修饰符：method.getModifiers()方法返回访问权限修饰符常量代码，是 int 类型，例如 1 代表 public，
     * 这些数字代表的含义可以通过Modifier.toString(int)方法转换为字符串
     */
    @Test
    public void testMethod2() throws Exception {
        Class clz = Apples.class;

        //获取方法修饰符、返回值、方法名
        System.out.println("\n" + "\n" + "获取方法修饰符、返回值、方法名：");
        Method[] declaredMethod = clz.getDeclaredMethods();
        for (Method method : declaredMethod) {
            System.out.print(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + method.getName());
            System.out.println();
        }

        //获取方法参数类型
        System.out.println("\n" + "获取setName参数：");
        Method setName = clz.getMethod("setName", String.class);
        Class<?>[] parameterTypes = setName.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType.getName());
        }

        //获取getArea方法上的所有注解
        System.out.println("\n" + "\n" + "获取getArea方法上的所有注解");
        Method getArea = clz.getMethod("getArea");
        Annotation[] annotations = getArea.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获取getArea方法上指定注解的值
        System.out.println("\n" + "获取getArea方法上指定注解的值");
        Color annotation = getArea.getAnnotation(Color.class);
        System.out.println(annotation);
        System.out.println(annotation.value());

    }


    /**
     * 反射调用指定方法
     *
     * @throws Exception
     */
    @Test
    public void testMethod3() throws Exception {
        Class clz = Apples.class;
        Apples a = (Apples) clz.newInstance();

        //调用public方法，方法名和参数决定方法对象
        Method m = clz.getMethod("setName", String.class);
        //传入对象及方法参数
        m.invoke(a, "红富士");

        Method getName = clz.getDeclaredMethod("getName");
        //getName虽然是private,如果是在另一个类中调用getName ,需要如下处理
        getName.setAccessible(true);
        String name = (String) getName.invoke(a);
        System.out.println(name);

    }


    /**
     * 获取类注解
     */
    @Test
    public void testClassAnnotation() {
        Class<Apples> clz = Apples.class;
        Annotation[] annotations = clz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        System.out.println();
        Annotation annotation = clz.getAnnotation(Color.class);
        Color color = clz.getAnnotation(Color.class);
        System.out.println(annotation);
        System.out.println(color);
    }
}


/**
 * 反射获得的是运行时类（class文件加载到内存并被解释执行）的相关信息，所以注解的有效期是RUNTIME时才能获取到
 */
@Color("red")
@Count(3)
class Apples extends Fruit implements Comparable<Apples> {
    public static void main(String[] args) {
        System.out.println(1);
        System.out.print("");
        // System.out.print(3);
    }

    public String name;
    double price;
    protected boolean sweet;
    private String area;

    public Apples() {
    }

    private Apples(String name) {
        this.name = name;
    }

    public Apples(String name, double price, boolean sweet, String area) {
        this.name = name;
        this.price = price;
        this.sweet = sweet;
        this.area = area;
    }

    @Color("green")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    boolean isSweet() {
        return sweet;
    }

    void setSweet(boolean sweet) {
        this.sweet = sweet;
    }

    @Override
    public String toString() {
        return "Apples{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", sweet=" + sweet +
                ", area='" + area + '\'' +
                '}';
    }

    @Override
    public int compareTo(Apples o) {
        return this.price - o.price > 0 ? 1 : 0;
    }
}


/**
 * @author epanhai
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Color {
    String value();
}


/**
 * @author epanhai
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Count {
    int value();
}

class Fruit {
    public String kind;

    public Fruit() {
    }

    public Fruit(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    void setKind(String kind) {
        this.kind = kind;
    }
}

