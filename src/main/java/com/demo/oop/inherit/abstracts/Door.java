package com.demo.oop.inherit.abstracts;

/**
面向抽象编程:上层代码只定义规范,具体的业务逻辑由不同的子类实现，调用者并不关心

抽象类：从设计层面看，抽象类体现继承关系（IS-A），它主要描述类的从属关系或者父子关系，
       抽象类和它的派生类之间是典型的IS-A关系，即“子is a父”。

接口：接口interface可以多实现，子类和interface定义在概念本质上可以是不一致的，
     仅仅是实现了interface定义的契约而已。它主要描述的是类型间的行为合同，接口和它的实现类之间是典型的CAN-DO关系，即“子can do父”。

对比：interface强调特定功能的实现，而abstract class强调所属关系。

入门案例：
门都有open( )和close( )两个动作，通过抽象类和接口来定义这个抽象概念。

public abstract class Door {
    public abstract void open();
    public abstract void close();
}


interface Door {
    public abstract void open();
    public abstract void close();
}


但是现在如果需要门具有报警alarm( )的功能

将这三个功能都放在抽象类里面，像火灾报警器本质并不具备开关功能，且和这个抽象类都不具备子is父的关系
将这三个功能都放在接口里面，就需要实全部现open( )和close( ) 以及alarm()接口，
也许有的门并不一定具备报警功能，有的类根本就不具备open( )和close( )功能，比如火灾报警器。

从这里可以看出， Door的open() 、close()和alarm()根本就属于两个不同范畴内的行为,所以不适合一起放在抽象类中
open()和close()属于门本身固有的行为特性，而alarm()属于延伸的附加行为。
因此最好的解决办法是单独将报警功能设计为一个接口，包含alarm()行为,Door设计为单独的一个抽象类，包含open和close两种行为。
报警门可以继承Door类和实现Alarm接口
 * @author epanhai
 */


public abstract class Door {
    public abstract void open();
    public abstract void close();
}

interface Alarm{

    /**
     * 报警功能
     */
    public abstract  void alarm();
}

class AlarmDoor extends Door implements Alarm{

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void alarm() {

    }
}




