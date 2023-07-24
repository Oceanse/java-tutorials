package com.demo.others.testNG.dependencyTest;

import org.testng.annotations.Test;

public class DependTest2 {

    @Test(dependsOnGroups = {"dependGroup1"})
    public void dependTest3() {
        System.out.println("dependTest3");
    }

    @Test(groups = {"dependGroup1"})
    public void dependTest1() {
        System.out.println("dependTest1");
    }

    @Test(groups = {"dependGroup1"})
    public void dependTest2() {
        System.out.println("dependTest2");
    }

}
