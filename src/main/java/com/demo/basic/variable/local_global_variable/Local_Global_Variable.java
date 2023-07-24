package com.demo.basic.variable.local_global_variable;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;


/**
 * 变量按照作用域分类：局部变量和全局变量
 * <p>
 * 局部变量(Local variable)
 * 定义：方法中的变量(包括方法体 形参)和代码块中的变量
 * <p>
 * 存储位置：栈内存中
 * 局部变量只是存放字面值或者对象的地址，因此占用的空间比较小
 * <p>
 * 作用范围：
 * 局部变量只在当前{}有效， 或者说只在当前{}可见，准确来说是从声明的位置开始到所在的花括号结束，局部变量只能在声明的位置之后使用
 * 局部变量的作用范围越小越好，作用范围越小，在内存停留的时间就越短，程序的性能就会越好
 * <p>
 * 生存时间：
 * 当局部变量所在的方法或者代码块被使用时，变量会被创建，当作用范围结束后，变量空间会立即自动释放，无需依赖JAVA的垃圾回收机制
 * <p>
 * <p>
 * <p>
 * 全局变量： 实例变量(Instance Variable)和静态变量(Static variable，详见statics模块)
 * <p>
 * 实例变量定义：类内方法外，不被static修饰，属于某个对象的属性
 * <p>
 * 存储位置：堆内存
 * <p>
 * 作用范围：
 * 在整个类中有效，在整个类中都可以被访问；因此实例变量可以在声明的前后位置使用；
 * <p>
 * 生存时间：随着对象创建而创建，随着对象消亡而消亡；当一个对象被实例化之后，每个实例变量的值就跟着确定
 * <p>
 * <p>
 * <p>
 * 初始化：
 * 实例变量：系统都会默认根据其数据类型进行默认初始化
 * 局部变量：系统默认不会对局部变量进行初始化数据操作
 * 变量使用前必须进行初始化；
 * 如果局部变量在使用前没有进行手动初始化则会在编译器报错，方法形参也是局部变量，会在调用方法传递实参的时候完成初始化
 * 如果局部变量进行了声明没有进行初始化， 但是也一直没有使用的话编译也是不会报错的
 * <p>
 * 命名：
 * 同一作用域，变量名不能重复(不能定义变量名相同的变量)；假设存在重名的两块栈内存区域，那么编译器无法把内存和对应的变量名进行匹配；
 * 栈内存和堆内存内的变量是可以重名的，因为他们属于两块不同的内存区域；就像是两个不同的包内的类可以重名，两个不同局域网内的私有ip可以重名
 * 访问没有this等前缀的变量时需要遵循的原则为：就近原则，首先在局部范围找，有就使用，没有接着在成员位置找，如果需要显式访问成员变量，可使用this
 *
 * @author epanhai
 */
public class Local_Global_Variable {


    /**
     * 实例变量，对象创建时候被分配空间，对象销毁后被释放
     */
    String name = "ocean";
    int age = 31;


    //全局变量具有默认初始值
    byte b;//0
    short s;//0
    int i;//0
    long l;//0L
    float f;//0.0F
    double d;//0.0
    boolean flag;//false
    char c;// '\u0000'  这种格式是一个char字符unicode码的写法;它只是看起来时空格，但是它与空格、空字符串、NULL和"null"都不同，所以无法找到能够描述它的符号

    //null是一种特殊的引用类型变量值或者引用类型字面值,牙科椅作为任意引用类型变量的变量值
    Object o;//null
    Runnable r;//null
    String[] names;//null

    static Date date;//null


    @Test
    public void testInitialValue() {
        out.println(b);
        out.println(s);
        out.println(i);
        out.println(l);
        out.println(f);
        out.println(d);
        out.println(flag);
        out.println(c);
        out.println((int) c);
        out.println();

        //引用变量对应的栈存储空间默认值为null, null可以理解为一种特殊的引用值，不指向任何堆空间；
        out.println(o);
        out.println(r);
        out.println(names);
        out.println(date);
    }


    /**
     * 局部变量只在当前{}有效， 或者说只在当前{}可见，准确来说是从声明的位置开始到所在的花括号结束
     * 局部变量只能在声明的位置之后使用
     */
    @Test
    public void testLocalVariableScope() {
        //System.out.println(hobby);
        String[] hobby = {"coding", "reading"};//hobby变量的作用域是从当前位置到方法体的结束，不包括当前行的上面
        System.out.println(hobby);
    }


    /**
     * 实例变量在整个类中有效，在整个类中都可以被访问, 因此实例变量可以在声明的前后位置使用
     */
    @Test
    public void testGlobalVariableScope() {
        System.out.println(this.height);
    }

    /**
     * 定义实例变量
     */
    double height = 173.0;

    /**
     * sum的作用域是整个方法，也就是只有整个方法调用结束时候变量sum就会被释放
     * i的作用域是for循环，for循环结束，i就会被释放
     * i和sum在方法被调用时候才会为其创建分配内存空间
     * 超出作用域后，占用的栈空间就会被释放
     */
    @Test
    public void testLocalVariableLifeCycle() {
        int age = 31;
        if (age == this.age) {
            //result作用域是当前{}
            String result = "right";
        }
        //System.out.println(result); //超出result作用域，result变量占用的栈空间已经被释放

        //sum作用域是当前位置到方法结束
        int sum = 0;
        //i的作用范围是for循环内部，只有在执行到for循环时候才会为i创建分配内存空间，for循环结束后i占用的内存空间被释放
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
        // System.out.println("i = " + i);//超出i作用域，i变量的栈空间在for循环结束后已经被释放
    }


    /**
     * 变量使用前必须初始化
     * 系统默认不会对局部变量进行初始化数据操作，如果局部变量在使用前没有进行初始化则会在编译器报错
     * 局部变量没有初始化，但是没有使用的话编译也是不会报错的
     */
    @Test
    public void testLocalVariableInitialize() {
        String[] products;
        //System.out.println(products); //变量没有初始化就使用,会直接编译报错

        double price = 100;
        double payment;
        String role = "vip";
        if ("vip".equals(role)) {
            payment = price * 0.9;
        }
        //System.out.println(payment); //这里payment可能未被初始化，会直接编译报错
    }


    /**
     * 变量使用前必须初始化；
     * 系统默认不会对局部变量进行初始化数据操作，如果局部变量在使用前没有进行初始化则会在编译器报错
     * 局部变量没有初始化，但是没有使用的话编译也是不会报错的
     */
    @Test
    public void testLocalVariableInitialize2() {
        //String[] hobby=null会对引用变量str(存在栈内存)赋值为null,但是不会开辟堆空间，str完成了初始化
        String[] hobby = null;
        if (hobby == null) {
            out.println("Life is boring");
        }
    }


    /**
     * 同一作用域，变量名不能重复(不能定义变量名相同的变量)；
     * 假设存在重名的两块栈内存区域，那么编译器无法把内存和对应的变量名进行匹配
     */
    @Test
    public void testDuplicateName() {
        //在同一个栈作用域内，变量名不能相同
        String programLanguage;
        //List<String> programLanguage;

        for (int i = 0; i < 10; i++) {
            //int i;
        }
    }


    /**
     * 局部变量可以和全局变量重名
     * 变量前面没有this或者super等修饰符，访问时会遵循就近原则
     * 如果需要显式访问成员变量，可使用this
     */
    @Test
    public void testDuplicateName2() {
        //局部变量可以和全局变量重名，这里的name分配在栈内存，成员变量name分配在堆内存
        String name = "tom";

        //当name前面没有this或者super等修饰符时候会遵循就近原则,所以这里的name是局部变量name
        System.out.println("name=" + name);

        //显式访问实例变量name
        System.out.println("this.name=" + this.name);
    }


    /**
     * 代码块中声明的变量是局部变量，仅在当前代码块内生效
     * 局部变量可以和全局变量重名
     * 变量前面没有this或者super等修饰符时候会遵循就近原则，如果需要显式访问成员变量，可使用this
     *
     */ {
        int age = 33;
        //当age前面没有this或者super等修饰符时候会遵循就近原则,所以这里的age是局部变量age
        System.out.println("age=" + age);
        //this修饰说明age是当前对象的一个属性
        System.out.println("this.age=" + this.age);
    }


    /**
     * 局部变量对应的栈内存无需依赖JAVA的垃圾回收机制，会随着方法或者代码块运行结束而自动释放
     */
    @Test
    public void testLocalVariable() {
        Map<String, String> products = new HashMap<>();
        products.put("茅台", "贵州茅台");
        products.put("国窖1573", "泸州老窖");
        products.put("洋河", "江苏洋河");
    }
}
