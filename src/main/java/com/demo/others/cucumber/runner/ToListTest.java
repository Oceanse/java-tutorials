package com.demo.others.cucumber.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/demo2/TodoStep.feature"},//指定feature文件的classpath
        glue = "com/demo/cucumber/step_definition/demo2"//指定java脚本的包路径
)
public class ToListTest extends AbstractTestNGCucumberTests {
}
