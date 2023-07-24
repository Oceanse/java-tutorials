package com.demo.others.testNG.dependencyTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * hard依赖：默认为此依赖方式，即其所有依赖的methods或者groups必须全部pass，否则被标识依赖的类或者方法将会被略过，在报告中标识为skip，
 * 如后面的范例所示，此为默认的依赖方式；
 * <p>
 * soft依赖：此方式下，其依赖的方法或者组有不是全部pass也不会影响被标识依赖的类或者方法的运行，注意如果使用此方式，则依赖者和被依赖者之间
 * 必须不存在成功失败的因果关系，否则会导致用例失败。此方法在注解中需要加入alwaysRun=true即可，
 * 如@Test(dependsOnMethods= {"TestNgLearn1"}， alwaysRun=true)；
 */
public class DependTest4 {
    @Test(dependsOnMethods = "dependTest4", groups = {"dependGroup1"})
    public void dependTest1() {
        System.out.println("dependTest1");
    }

    @Test
    public void dependTest4() {
        System.out.println("dependTest4");
        Assert.assertFalse(true);
    }
}
