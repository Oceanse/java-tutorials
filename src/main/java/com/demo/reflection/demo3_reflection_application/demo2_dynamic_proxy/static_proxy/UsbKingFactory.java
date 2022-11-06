package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

/**
 * 目标类:金士顿厂家,不接受用户的单独购买
 */
public class UsbKingFactory implements usbSell {
    public float sell(int amount) {
        return 85.0f * amount;
    }
}