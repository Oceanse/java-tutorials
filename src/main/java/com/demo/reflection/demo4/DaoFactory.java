package com.demo.reflection.demo4;


import java.awt.print.Book;

public class DaoFactory {

        private static final DaoFactory factory = new DaoFactory();
        private DaoFactory(){}

        public static DaoFactory getInstance(){
            return factory;
        }

    /**
     *
     * @param className
     * @param clazz 通过这个参数可以在调用方法时候指明T的具体类型
     * @return
     * @param <T>
     */
        public <T> T createDao(String className, Class<T> clazz){
            try{
                T t = (T) Class.forName(className).getConstructor().newInstance();
                return t;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    public static void main(String[] args) {
        Book book = DaoFactory.getInstance().createDao("java.awt.print.Book", Book.class);
    }

    }
