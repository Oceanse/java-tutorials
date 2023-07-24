package com.demo.lambda.stream;

import org.testng.annotations.Test;

import java.util.stream.Stream;

public class StreamDemo3 {

    /**
     * 串行流
     */
    @Test
    public void testParallelStream(){
        Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9);
        Integer sum = stream.reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);
    }

    /**
     * 并行流
     */
    @Test
    public void testParallelStream2(){
        Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9);
        Stream<Integer> parallelStream = stream.parallel();//串行流转并行流
        Integer sum = parallelStream.reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);
    }


    /**
     * peek()方法是专门用来调试的方法，是中间操作，这里能看到所有的数据都是main线程完成
     * 并行流在数据非常大的时候会有明显的效率提升
     */
    @Test
    public void testParallelStream3(){
        Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9);
        Integer sum = stream.peek(n-> System.out.println(n+Thread.currentThread().getName())).reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);
    }


    /**
     * peek()方法是专门用来调试的方法，是中间操作，这里的所有数据由几个线程配合完成，最后把结果合并；
     * 并行流在数据非常大的时候会有明显的效率提升
     */
    @Test
    public void testParallelStream4(){
        Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9);
        Stream<Integer> parallelStream = stream.parallel();
        Integer sum = parallelStream.peek(n-> System.out.println(n+Thread.currentThread().getName())).reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);
    }

}
