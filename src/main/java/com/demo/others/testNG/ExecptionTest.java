package com.demo.others.testNG;

import org.testng.annotations.Test;

public class ExecptionTest {
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "NullPoint")
    public void testException() {

        throw new IllegalArgumentException("NullPoint");
    }
}
