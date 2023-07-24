package com.demo.oop.inherit.demo2_this_super.demo1;

/**
 * super作用：
 * 1 隐式或者显式调用父类构造方法,某些情况下父类属性私有的，所以可以通过super显式的的调用父类空参完成继承来的私有属性初始化,
 *   也就是子类通过super间接拥有父类私有属性或者对父类私有属性进行间接读写访问，注意通过super调用父类构造器的前提是父类构造器不能是private
 * 2 访问父类的非private成员变量和方法,此时的super可以理解为当前对象的父对象，
 *   子类和父类中变量或方法名称相同时，用super关键字来访问父类属性和方法，当然前提是父类成员不是private
 *
 * Note:
 * 子类一定会隐式或者显式调用父类构造器，也就是若不显示调用父类构造器，编译器会自动在子类构造方法的第一句加上super()，也就是要先创建父类对象，然后才创建子类对象，因为子类需要继承父类的某些实例成员
 * 父类最好保留空参构造来供子类调用
 * 若显示调用父类带参构造器，则不会调用super()，同样遵循了先创建父类对象，然后才创建子类对象的原则
 * super()或者super(参数)只能出现在构造函数中(构造方法不允许是静态的)，不能出现在普通方法中
 * super调用父类属性或者方法时候代指当前对象的父类对象，所以super调用方法属性时候不能出现static方法中，因为父类对象可能还没生成
 * super只能出现在构造方法的第一行
 */
public class Coder extends Person {

    /**
     * 新增属性
     */
    private double salary;


    /**
     * 如果子类的构造方法中没有显示地调用父类构造方法，则系统默认调用父类无参数的构造方法
     */
    public Coder() {
        super(); //这里无论显示或者隐式调用，必须保证父类中有空参构造方法
        System.out.println("Coder() is invoked");
    }


    public Coder(double salary) {
        //super(); 这里会隐式调用super()，必须保证父类中有空参构造方法
        this.salary = salary;
        System.out.println("Coder(double salary) is called");
    }



    /**
     * 子类是不继承父类的构造器（构造方法或者构造函数）的，它只是调用（隐式或显式）
     * 显式的调用父类参数的构造方法 public Person(String name, int age, boolean married)
     *
     * @param name
     * @param age
     * @param married
     * @param salary
     */
    public Coder(String name, int age, boolean married, double salary) {
        //不再调用super()，因为显示的调用了父类的三参构造器
        //因为父类的属性都是私有的，所以不能采取"this.name=name"这种方式赋值；但是可以通过super调用父类的构造方法去初始化间接拥有的私有属性；
        //这里同时也创建了一个父类对象
        super(name, age, married);
        this.salary = salary;
        System.out.println("Coder(String name, int age, boolean married, double salary) is called");
    }


    /**
     * 重写父类info()方法,然后当前类的对象拥有重写后的info方法，重写前的info方法也可以理解成依然存在于当前类，但是只能通过super调用
     * 因为父类的属性都是私有的，子类虽然不能继承父类私有属性，但是可以通过继承过来的getter setter方法访问和修改私有属性
     * @Override可以让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错
     */
    @Override
    public void info() {
        System.out.println("Coder info: "+ getName() + " is " + getAge() + " years old and salary is" + salary+" and is he married: "+isMarried());
    }

    /**
     * 添加子类独有的新方法
     */
    public void post() {
        System.out.println("My post is senior software engineer");
    }




    /**
     *  调用当前对象的info()方法,也就是重写后的方法
     *  new Coder().info()等价于new Coder().accessCoderInfo()
     */
    public void accessCoderInfo() {
        this.info();
    }


    /**
     * 调用当前类对象的父类对象的info()方法,也就是重写前的方法
     */
    public void accessHumanInfo() {
        //System.out.println(super.name); //这里不能通过super访问父类中的private成员
        super.info();
    }


    public static void main(String[] args) {

        //先调用父类构造方法Human()，再调用本类构造方法
        Coder coder = new Coder();
        coder.accessCoderInfo(); //调用当前对象的info()，重写后的info()
        coder.accessHumanInfo();//调用重写前的info()

        System.out.println();
        Coder coder2 = new Coder(20000);
        coder2.accessCoderInfo(); //调用当前对象的info()
        coder2.accessHumanInfo();//调用当前对象的父类对象的info()方法，当前coder对象的父类对象由Person()创建

        //先调用父类构造方法Person(String name, int age, boolean married)，再调用本类构造方法
        System.out.println();
        Coder coder3 = new Coder("ocean", 30, true, 20000);
        coder3.accessCoderInfo();//调用当前对象的info()
        coder3.accessHumanInfo();//调用当前对象的父类对象的info()方法， 当前coder2对象的父类对象由Person(String name, int age, boolean married)创建
    }


}

