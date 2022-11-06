package com.demo.basic.array;

import com.demo.basic.keywords.finals.unmutable_instance.demo2.Fruit;
import com.demo.oop.inherit.demo2_this_super.Person;
import com.demo.pojo.Resource;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * 数组是一种常见的单一类型容器类数据结构,是多个相同类型数据的组合(元素类型必须唯一)，元素包括基本数据类型和引用数据类型
 *
 * 数组一旦分配堆空间，长度将不可改变。即使把某个数组元素的数据清空，但它所占的空间依然被保留，依然属千该数组，数组的长度依然不变.String[] str=new String[length];
 *
 * 数组也是一种数据类型，它本身是一种引用类型。例如int是一个基本类型，但int[]这是定义数组的一种方式）就是一种引用类型了, String[] names中names就是一种引用类型变量，指向数组对象。
 * 数组变量是一个引用变量，存在于栈中，保存的是数组首元素的内存地址或者理解为指向堆内存中对应的数组对象；只有通过引用变量才能访问和修改数组元素
 *
 * 数组定义:type[] arrayName; type[]是数组类型，这里仅仅表示定义了一个引用变量（也就是定义一个指针），这个引用变量还未指向任何有效的堆内存，因此定义数组时不能指定数组的长度
 *
 * 数组初始化：
 *      默认初始化和手动初始化, 数组只有初始化之后才能被使用
 *          默认初始化: char[] letters=new char[10];  这里开辟栈堆空间，并且系统会进行默认初始化
 *          手动初始化：char[] letters={'a','b'};
 *
 *      动态初始化:声明并指定数组长度,之后再进行手动赋值(堆空间分配和手动赋值分开进行)，eg: char[] letters=new char[1]; letters[0]='a'
 *      静态初始化:在数组创建时候同时手动赋值初始化，由系统决定数组的长度(堆空间分配和手动初始化同时进行)eg: char[] letters=new char[]{'a','b'}或者char[] letters={'a','b'};
 *               简化格式： 数据类型[] 数组名称 = {值, 值, …};
 *               完整格式（推荐）: 数据类型[] 数组名称 = new 数据类型[]{值, 值, …}
 *
 *       必须牢记：定义并初始化一个数组后， 在内存中分配了两个空间， 一个在栈中存放数组的引用变量， 另一个在堆中存放数组本身
 *
 * 数组遍历：for，foreach, lambda,  Arrays.toString
 *
 * 数组元素都有下标，范围从0到length-1,数组通过下标访问数组元素
 *
 * 数组查询效率很高
 *
 * 数组一旦分配空间，其长度length是不可变的
 */
public class ArrayDemo {

    /**
     * 声明数组
     */
    @Test
    public void test1() {
        //这里只是声明int[]/double[]/char[]/String[]类型的数组，要把int[]/double[]/char[]/String[]理解成一种引用数据类型，
        // 而num,scores等只是引用类型变量，这里还没有指向堆内存中对应的数组对象，也就是num等变量还没有初始化
        //只是分配了栈内存空间，不能指定数组长度
        int[] num;
        double[] score;
        char[] letters;
        String[] names;
        com.demo.basic.code_block.construct_block.demo1.Person[] people;
        //System.out.println(num); //变量初始化之前不能被使用
    }



    /**
     * 动态初始化数组：先分堆配空间(默认初始化)，然后程序员手动赋值(手动初始化)
     */
    @Test
    public void test2() {

        //先分配空间(默认初始化)
        int[] arr;      //声明数组，即声明一个int[] 类型的数组arr,这里只是占用栈内存空间;
        arr = new int[5];//分配内存空间 数组名 = new 类型[数组元素的个数] 开辟堆内存空间，这里系统已经默认初始化完
        System.out.println(Arrays.toString(arr));//数组元素默认全是0

        //手动赋值(初始化)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 2 * i; //把每个字面值赋值给数组元素空间
        }
        System.out.println(Arrays.toString(arr));


        //先分配空间(默认初始化)
        Person[] p = new Person[3];//声明同时分配内存空间
        System.out.println(Arrays.toString(p));//数组元素默认全是null

        //手动赋值(初始化)
        for (int i = 0; i < p.length; i++) {
            p[i] = new Person();//把每个对象地址赋值给数组元素空间
        }
        System.out.println(Arrays.toString(p));
    }



    /**
     * 静态初始化数组：堆空间分配和手动赋值(手动初始化)同时进行，由系统决定数组长度
     */
    @Test
    public void test2_2() {

        //堆空间分配和手动赋值(初始化)同时进行
        String[] str = {"aa", "bb"};
        //这里不能指定数组的长度，系统会自动推算出数组的长度
        String[] str2 = new String[]{"aa", "bb"};

        //分配空间和手动赋值(初始化)同时进行
        Resource t1 = new Resource();
        Resource t2 = new Resource();
        Resource t3 = new Resource();
        //tt数组的每个空间中保存着t1,t2,t3对象的地址
        Resource[] tt = {t1, t2, t3};
        //tt2数组的每个空间中保存着t1,t2,t3对象的地址
        Resource[] tt2 = new Resource[]{t1, t2, t3};

        //分配空间和手动赋值(初始化)同时进行
        Date[] date = {new Date(20190719), new Date(20190720)};
        Date[] date2 = new Date[]{new Date(20190719), new Date(20190720)};

        //空数组实例：长度为0的数组,因为数组的长度是固定的，所以不能继续添加元素
        Fruit[] fruits={};
        //等价于
        Fruit[] fruits2=new Fruit[0];
        System.out.println("fruits = " + fruits.length);
        System.out.println("fruits = " + fruits.length);


        /*
        java11类型推断: 程序更加简洁
        编译器推断names地址的类型是String[]
        var namesArr = new String[] {"tom"} ;
        编译器推断weightArr变量的类型是double[]
        var weightArr = new double[4];
        使用静态初始化简化语法执行初始化的数组不能使用var定义数组变量, var namesArr ={"tom"};是错误的
         */
    }




    /**
     * 栈变量，数组引用变量和数组对象
     */
    @Test
    public void test2_3() {
        Mouse m1=new Mouse("black");
        Mouse m2=new Mouse("white");

        //数组中两个元素类型也是引用类型，而且两个元素和m1，m2分别指向同一对象
        Mouse[] mouse;
        // mouse[0]=m1;  mouse[1]=m2;
        mouse= new Mouse[]{m1, m2};



        //通过数组元素或者m1,m2修改Mouse访问到的对象是相同的
        System.out.println(mouse[0].getColor());
        System.out.println(m1.getColor());

        //通过数组元素或者m1,m2修改Mouse对象时是等价的
        mouse[0].setColor("blue");
        System.out.println(mouse[0].getColor());
        System.out.println(m1.getColor());

        //通过数组元素或者m1,m2修改Mouse对象时是等价的
        m1.setColor("red");
        System.out.println(mouse[0].getColor());
        System.out.println(m1.getColor());

    }





    /**
     * 数组引用变量和数组对象, 数组元素类型必须是数组类型或者数组类型的子类型
     */
    @Test
    public void test2_4() {
        //数组元素类型必须属于Nember类或者其子类
        //而number只是引用类型变量，指向堆内存中的首个数组对象的地址
        Number[] number=new Number[]{new Double(1.0), new Integer(1)} ;

        number=null;//切断数组引用变量和数组对象的联系，此时没有任何引用指向数组对象，上面数组对象将会变成垃圾被回收
    }


    /**
     * 数组引用变量和数组对象
     */
    @Test
    public void test2_5() {
        //数组作为引用数据类型，也可以发生引用传递,不同的栈引用可以指向同一块堆内存
        char[] c = new char[]{'a', 'b', 'c'};
        char[] c2;
        c2 = c;// 两个栈变量c和c2指向同一个堆数组对象，也就是数组对象有两个句柄指向引用它
        System.out.println(Arrays.toString(c2));
        c2[0] = 'w';
        System.out.println(c[0]);
    }




    /**
     * 如果堆内存中数组不再有任何引用变量指向自己，则这个数组将成为垃圾， 该数组所占的内存将会被系统的垃圾回收器回收;
     * 看待一个数组时，一定要把数组看成两个部分： -部分是数组引用，也就是在代码中定义的数组引用变量；
     * 还有一部分是实际的数组对象,这部分是在堆内存里运行的，通常无法直接访问它，只能通过数组引用变量来访问
     */
    @Test
    public void test2_6() {
        Object[] o=new Object[]{new Double(1.0), new Resource()} ;
        o=null;//切断数组引用变量和数组对象的联系，此时没有任何引用指向数组对象，上面数组对象将会变成垃圾被回收
    }




    /**
     * 通过索引访问数组元素
     * 索引起点：0
     * 索引终点：length-1
     */
    @Test
    public void test3() {
        double[] score = {110, 118.5, 120};
        System.out.println(score[0]);
        System.out.println(score[1]);
        System.out.println(score[2]);

        //编译程序不会出现任何错误，但运行时出现异常： java.lang.ArrayindexOutOtBoundsException
        System.out.println(score[3]);

    }


    /**
     * 通过for遍历/迭代数组元素
     */
    @Test
    public void test3_2() {
        String[] fruits = new String[]{"apple", "orange", "banana"};
        for (int i = 0; i < fruits.length; i++) {
            System.out.println(fruits[i]);
        }
    }


    /**
     * 通过foreach遍历/迭代数组元素(不需要索引)
     * 当使川foreach 来迭代访问数组元素时， foreach 中的循环变量是一个临时变量
     * 系统会把数组元素依次赋给这个临时变量
     * 这个临时变量可以理解为集合元素的副本，如果集合元素是基本类型，那么通过变量名操作元素不会影响原数组中元素的值
     * 总结：使用foreach 循环迭代数组元素时， 并不能改变数组元素的值
     */
    @Test
    public void test3_3() {
        double[] scores = new double[]{100.0, 120.0, 130};
        System.out.println(Arrays.toString(scores));

        for (double score : scores) {//相当于把每个数组元素的值的副本传递给新的变量score
            score=2*score;
        }

        System.out.println(Arrays.toString(scores));  //scores数组元素本身没有改变


        /*
        java11可以类型自动推断
        * Book[] books={xxx}
        * for(var book:books){
        *   System.out.print(book + " ");
        * }
        * */

    }


    /**
     * for(类型 变量名:集合)
     * 当使川foreach 来迭代访问数组元素时， foreach 中的循环变量是一个临时变量
     * 这个临时变量可以理解为数组元素的副本，通过变量名操作元素不会影响原集合中元素的变量值(无论是字面值或者引用值)
     * 但是如果集合元素是引用类型，也就是数组元素保存的是对象的地址，那么通过临时变量名操作元素虽然不能影响对象的地址，但是可以影响对象的内容
     * 总结：使用foreach 循环迭代数组元素时， 并不能改变数组元素的值
     */
    @Test
    public void test3_4() {
        com.demo.basic.code_block.construct_block.demo1.Person p1 = new com.demo.basic.code_block.construct_block.demo1.Person("ocean", 31);
        com.demo.basic.code_block.construct_block.demo1.Person p2 = new com.demo.basic.code_block.construct_block.demo1.Person("phy", 32);
        //personList存放的是对象的内存地址
        com.demo.basic.code_block.construct_block.demo1.Person[] personList = new com.demo.basic.code_block.construct_block.demo1.Person[]{p1, p2};


        //遍历对象内容
        for (com.demo.basic.code_block.construct_block.demo1.Person person : personList) {
            System.out.println(person.getName() + ":" + person.getAge());
        }
        System.out.println();

        for (com.demo.basic.code_block.construct_block.demo1.Person person : personList) {//相当于把每个数组元素的值的副本(对象的地址)传递给新的变量person，相当于每一个数组对象又多了一个句柄
            //这里不会改变原数组中元素的变量值(对象地址)，但是这里新的引用变量person也会指向原列表元素所指向的对象，相当于每一个对象又多了一个句柄，
            // 所以多个句柄指向一个对象时候，有一个句柄修改对象时候，另一个句柄就能看到对象内容的变化
            person.setName("Pan HaiYang");
            person.setAge(33);
        }


        //遍历对象内容
        for (com.demo.basic.code_block.construct_block.demo1.Person person : personList) {
            System.out.println(person.getName() + ":" + person.getAge());
        }
    }



    /**
     * 通过foreach遍历/迭代数组元素(不需要索引)
     * 不会修改数组或者集合的元素的值，这里的话就是数组元素永远指向常量池中固定的对象，
     * 又因为String是不可变类，所以数组元素永远指向常量池中固定的对象，且这个对象的内容也不会被改变
     * 总结：使用foreach 循环迭代数组元素时， 并不能改变数组元素的值
     */
    @Test
    public void test3_5() {
       //这里fruits中的数组元素变量保存的实际上是常量池中字符串的地址
        String[] fruits = new String[]{"apple", "orange", "banana"};
        //相当于把每个数组元素的值传递给给新的变量fruit，这里数组元素的值个人理解是常量池中字符串的地址，
        //也就是把常量池中字符串的地址赋值给了新的变量fruit，临时变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串对象
        for (String fruit : fruits) {
            //虽然新的变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串，但是String是不可变类，也就是常量池中的字符串内容不可改变
            //所以当其中一个句柄试图修改字符串内容时候，本质是会创建一个新的字符串，但是句柄会仍然指向修改之前的字符串
            //所以这里fruit.toUpperCase()之后，fruit将会指向新的字符串对象，数组元素还是指向原先的常量池中的字符串对象，因此原数组元素的值不会变
            System.out.println(fruit.toUpperCase());
            System.out.println(fruit);
        }
        System.out.println();
        //fruits数组元素本身没有改变
        System.out.println();
        for (String fruit : fruits) {
            System.out.print(fruit+" ");
        }
    }



    /**
     * Arrays.toString遍历数组内容
     */
    @Test
    public void test4() {
        int[] id = new int[]{1, 2, 3};

        //直接使用toString方法只会打印出数组的地址
        System.out.println(id);
        System.out.println(id.toString());

        //打印数组内容形式为[数组元素1，数组元素2...]
        System.out.println(Arrays.toString(id));
    }


    /**
     * Arrays.toString遍历查看数组默认内容
     */
    @Test
    public void test4_2(){
        System.out.println(Arrays.toString(new int[3]));
        System.out.println(Arrays.toString(new double[3]));
        System.out.println(Arrays.toString(new boolean[3]));
        System.out.println(Arrays.toString(new char[3]));//默认值是'\u0000'
        System.out.println(Arrays.toString(new String[3]));
        System.out.println(Arrays.toString(new com.demo.basic.code_block.construct_block.demo1.Person[3]));
        System.out.println(Arrays.toString(new Integer[3]));

    }


    /**
     * 数组stream化后可通过lambda遍历
     */
    @Test
    public void test4_3() {
        double[] score = {100, 110, 120};

        //Arrays.streamS  数组stream
        Arrays.stream(score).forEach(item -> System.out.println(item));
    }


    /**
     * 通过索引访问修改数组内容
     */
    @Test
    public void test5() {
        final String[] FRUITS = new String[]{"apple", "orange", "banana"};//这里fruits中的数组元素变量保存的实际上是常量池中字符串的地址
        System.out.println(Arrays.toString(FRUITS));
        FRUITS[0]="grape";//第一个元素实际上会指向常量池中新的字符串
        System.out.println(Arrays.toString(FRUITS));
    }



    /**
     * 二位数组可以看成一种特殊的一维数组，其中每个元素又是一个一维数组
     */
    @Test
    public void test3_9() {

        //数据类型[][] 数组名 = new 数据类型[二维数组的长度/包含的一维数组的个数][每个一维数组的长度];
        double[][] d = new double[2][3];//定义了一个整型的二维数组，其中包含2个一维数组，每个一维数组可以存储3个整数

        int[][] d2 = {{1, 2, 3}, {4, 5, 6}};//=左边声明部分不允许指定长度
        System.out.println(d2.length);
        for (int i = 0; i < d2.length; i++) {
            for (int j = 0; j < d2[i].length; j++) {
                System.out.print(d2[i][j] + " ");
            }
            System.out.println();
        }
    }



}

class Mouse{
    String color;

    public Mouse(String color){
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
