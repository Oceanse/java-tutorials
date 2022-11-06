package com.demo.testNG.dependencyTest;

import org.testng.annotations.Test;

public class DependTest1 {


    @Test(dependsOnMethods = {"dependTest1", "dependTest2"})
    public void dependTest3() {
        System.out.println("dependTest3");
    }

    @Test(dependsOnMethods = "dependTest1")
    public void dependTest4() {
        System.out.println("dependTest4");
    }

    @Test
    public void dependTest1() {
        System.out.println("dependTest1");
    }

    @Test
    public void dependTest2() {
        System.out.println("dependTest2");
    }


}
