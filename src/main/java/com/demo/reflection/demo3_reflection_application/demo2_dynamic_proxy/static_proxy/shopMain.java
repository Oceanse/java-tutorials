package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.static_proxy;

/**
 * https://www.cnblogs.com/jia0504/p/13811424.html
 * 静态代理优点：
 * 实现简单
 *
 * 静态代理缺点：
 * 当目标类增加了,代理类可能也需要成倍的增加
 * 当你的接口中功能在增加了,或者修改了,会影响众多的实现类,厂家类,代理都需要修改,影响比较多
 */
public class shopMain {
    public static void main(String[] args) {
         //创建代理的商家淘宝对象
        TaoBao taoBao = new TaoBao();
        //我只向淘宝买一件产品,得到报价
        float price = taoBao.sell(2);
        System.out.println("购买一件产品.淘宝的报价为: " + price);
    }
}