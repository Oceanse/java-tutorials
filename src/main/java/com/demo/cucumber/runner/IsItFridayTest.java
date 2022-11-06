package com.demo.cucumber.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/demo1/is_it_friday_yet.feature"},//指定feature文件的classpath
        glue = "com/demo/cucumber/step_definition/demo1"//指定java脚本的包路径

)
public class IsItFridayTest extends AbstractTestNGCucumberTests {

}
