package com.demo.oop.inherit.demo1_reuse.demo2;

/**
 * Manager 继承扩展 Employee
 * @author epanhai
 */
public class Manager extends Employee{

    /**
     * 奖金
     */
    private double bonus;

    public Manager(String name, double salary, double bonus) {
        //父类的私有属性name和salary只能通过super初始化
        super(name, salary);
        this.bonus = bonus;
    }


    /**
     * 设置奖金
     * @param bonus
     */
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }



    /**
     * 获取奖金
     * @return
     */
    public double getBonus() {
        return this.bonus;
    }



    /**
     * 经理还有年终奖，所以要重写父类方法
     * 子类重写了父类的 getSalary()方法，然后就拥有了这个重写后的方法；
     * 重写前的方法可以认为还会存在子类中，只不过只能通过super访问
     * @return
     */
    @Override
    public double getSalary() {
        return this.bonus+super.getSalary();
    }

    public static void main(String[] args) {
        Manager manager = new Manager("mxz", 1000000, 300000);
        System.out.println(manager.getId());
        System.out.println(manager.getName());
        System.out.println(manager.getBonus());
        System.out.println(manager.getSalary());
        System.out.println(Manager.getNameById(1));
        System.out.println(Manager.getSalaryById(1));    }
}
