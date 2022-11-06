package com.demo.enums.demo2;

public enum ErrorCodeEnumDemo {
    OK(0, "成功"),
    ERROR_A(100, "错误A"),
    ERROR_B(200, "错误B");

    ErrorCodeEnumDemo(int number, String msg) {
        this.code = number;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ErrorCodeEn{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
