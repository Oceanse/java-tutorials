package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

/**
 * 代理类：京东是一个商家代理对象,代理厂家目标对象
 */
public class JD implements UsbSell {

    // 声明商家代理的厂家
    private UsbKingFactory factory = new UsbKingFactory();


    @Override
    public double sell(int amount) {
        //发送给工厂,我需要的订单,返回报价
        double price = factory.sell(amount);
        //在目标类的方法调用后,你做的其他功能,都是增强的意思,这里商家需要加价也就是代理要增加价格
        price = price + 10;
        return price;
    }
}
