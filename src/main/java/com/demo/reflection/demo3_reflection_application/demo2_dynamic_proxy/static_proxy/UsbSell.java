package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

/**
 * 接口
 */
public interface UsbSell {

        /**
         * @param amount 表示一次购买的数量
         * @return 返回值表示一个u盘的价格
         */
        double sell(int amount);

}
