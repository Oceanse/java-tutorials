package com.demo.basic.keywords.statics.static_method.demo2;

/**
 * 工具类中的方法定义为静态方法，可以方便编程使用
 */
class MathUtility {

    public static double add(double m, double n) {
        return m + n;
    }

    public static double minus(double m, double n) {
        return m - n;
    }


    public static double multiply(double m, double n) {
        return m * n;
    }

    public static double divide(double m, double n) {
        if (n == 0) {
            System.out.println("除数不能为0");
            return -1;
        }
        return m / n;
    }


    /**
     * 绝对值
     * @param d
     * @return
     */
    public static double abs(double d) {
        return d < 0 ? -d : d;
    }

    /**
     * 平方
     * @param d
     * @return
     */
    public static double square(double d) {
        return d * d;
    }


    /**
     * 幂运算 m^n
     *
     * @param m
     * @param n
     * @return
     */
    public static double pow(double m, int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result = result * m;
        }
        return result;
    }


    public static int factorial(int number){
        int result=1;
        for (int i = 1; i <= number; i++) {
            result*=i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }

}
