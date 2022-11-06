package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

import com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy.UsbKingFactory;
import com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy.usbSell;

/**
 * 代理类：淘宝是一个商家,代理金士顿U盘的销售
 */
public class TaoBao implements usbSell {
    // 声明商家代理的厂家
    private UsbKingFactory factory = new UsbKingFactory();

    @Override
    public float sell(int amount) {
        //发送给工厂,我需要的订单,返回报价
        float price = factory.sell(amount);

        //商家需要加价也就是代理要增加价格
        price = price + 25;
        //在目标类的方法调用后,你做的其他功能,都是增强的意思
        System.out.println("淘宝再给你返回一个优惠券,或者红包");
        return price;
    }
}