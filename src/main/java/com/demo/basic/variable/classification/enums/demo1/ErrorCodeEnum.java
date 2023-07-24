package com.demo.basic.variable.classification.enums.demo1;

public enum ErrorCodeEnum {
    OK(200, "成功"),//OK本质是枚举类对象引用， public static final ErrorCodeEnum OK=new ErrorCodeEnum(200,"成功")；
    CLIENT_ERROR(400, "客户端错误A"),
    SERVER_ERROR(500, "服务端错误B");

    private int code;
    private String msg;

    /**
     * 构造方法：枚举类不支持通过程序来手动创建新实例，其构造方法的访问限制符只能为private，或者不加访问限制符(那么默认就是private)
     * 静态常量在类加载时候就会被初始化，所以这里的构造函数在类加载时候就会被初始化
     *
     * @param number
     * @param msg
     */
    ErrorCodeEnum(int number, String msg) {
        System.out.println("constructor is invoked");
        this.code = number;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 重写toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "ErrorCodeEn{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }

    /**
     * values()方法的作用就是获取枚举类中的所有常量值(所有Color对象的引用)，并作为数组返回
     * 也就是返回枚举常量数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
     */
    public static void testToString() {
        ErrorCodeEnum[] errorCodeEnums = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : errorCodeEnums) {
            System.out.println(errorCodeEnum);
        }
    }


    /**
     * 枚举类不支持通过程序来手动创建新实例，也就是只有系统能调用枚举类构造函数来创建枚举类对象
     */
    public static void testConstructor() {
        //ErrorCodeEnum errorCodeEnum=new ErrorCodeEnum(1,"");
    }

    /**
     * values()方法的作用就是获取枚举类中的所有常量值(所有Color对象的引用)，并作为数组返回
     * 也就是返回枚举常量数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
     */
    public static void testGet() {
        ErrorCodeEnum[] errorCodeEnums = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : errorCodeEnums) {
            System.out.println(errorCodeEnum.getCode() + ":" + errorCodeEnum.getMsg());
        }
    }

    public static void main(String[] args) {
        testToString();
        System.out.println();
        testGet();
    }
}
