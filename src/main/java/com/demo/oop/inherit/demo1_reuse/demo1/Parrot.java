package com.demo.oop.inherit.demo1_reuse.demo1;


/**
 * 脑部当前类的全部属性和方法
 */
public class Parrot extends Bird {

    String language;

    public Parrot() {
        //这里会默认调用父类Bird的空参构造函数: super();
        System.out.println(" public Parrot() is invoked");
    }

    /**
     * /Parrot类拥有本类和Bird类、Animal类全部的属性
     * @param species
     * @param food
     * @param swingsShape
     * @param language
     */
    public Parrot(String species,String food,String swingsShape, String language) {
        //这里会默认调用父类Bird的空参构造函数,Bird的空参构造函数被调用时候又会调用Animal空参构造函数(像是多米诺骨牌)
        System.out.println("Parrot(String species,String food,String swingsShape, String language) is invoked");
        this.species=species;
        this.food=food;
        this.swingsShape=swingsShape;
        this.language=language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void say() {
        System.out.println("Imitate human speech...");
    }

}
