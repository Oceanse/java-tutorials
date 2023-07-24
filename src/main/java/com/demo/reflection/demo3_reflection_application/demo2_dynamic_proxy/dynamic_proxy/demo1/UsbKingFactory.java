package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo1;

/**
 * 第二步:创建目标类实现接口
 */
public class UsbKingFactory implements UsbSell {
    @Override
    public float sell(int amount) {
        System.out.println("目标类中,执行了sell目标方法");
        return 85.02f;

    }
}
