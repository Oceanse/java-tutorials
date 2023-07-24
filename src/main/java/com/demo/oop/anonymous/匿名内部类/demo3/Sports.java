package com.demo.oop.anonymous.匿名内部类.demo3;

/**
 * 匿名内部类可以用在具体类、抽象类、接口上
 * 语法：new 类名(){...} 或者new 类名(参数列表){...}
 * 匿名内部类存在的前提是要有继承或者实现
 */
public class Sports {

    public Sports() {
    }

    public Sports(String kind) {
        this.kind = kind;
    }

    String kind;

    public void introduce() {
        System.out.println("show some sports info about " + kind);
    }


}

class TestSports {
    public static void showSports(Sports sports) {
        sports.introduce();
    }

    public static void main(String[] args) {

        //以匿名内部类的形式创建普通父类的子类实例：new 类名(){...}
        showSports(new Sports() {
            //匿名内部类中没有重写或者扩展任何方法,直接继承原有的introduce方法
        });

        //以匿名内部类的形式创建普通父类的子类实例：new 类名(){...}
        showSports(new Sports() {
            @Override
            public void introduce() {
                System.out.println("sports kind need to be specified");
            }
        });

        //以匿名内部类的形式创建普通父类的子类实例：new 类名(参数列表){...}
        showSports(new Sports("basketball") {
            //匿名内部类中直接继承原有的introduce方法

            //匿名内部类中增添新的方法
            public void introduce2() {
                System.out.println("This is basketball");
            }
        });
    }
}