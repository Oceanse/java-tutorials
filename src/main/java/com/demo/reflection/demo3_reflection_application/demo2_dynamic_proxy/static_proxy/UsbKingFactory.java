package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

/**
 * 目标类:金士顿厂家,不接受用户的单独购买，只能通过代理商家购买
 * 一个目标类可以对应多个代理商家
 */
public class UsbKingFactory implements UsbSell {
    public double sell(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("数量必须是正整数");
        }
        double price = amount > 1000 ? 30 : 35;
        return price;
    }
}