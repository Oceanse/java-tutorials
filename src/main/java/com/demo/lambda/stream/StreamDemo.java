package com.demo.lambda.stream;

import com.demo.collection_map.model.comparable_model.Students3;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamDemo {

    /**
     * 创建Stream
     */
    @Test
    public void createStream() throws FileNotFoundException {
        // 1. 通过Collection系列集合的 stream() 方法或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();//并行流

        // 2. 通过 Arrays 类中的静态方法 stream() 获取数组流
        String[] strArr = new String[10];
        Stream<String> stream1 = Arrays.stream(strArr);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        Stream<String> stream3 = Stream.of(strArr);

        //4. 使用 BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader reader = new BufferedReader(new FileReader("pom.xml"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);

        //5. 使用 Pattern.splitAsStream() 方法，将字符串分隔成流
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }


    /**
     * stream流中间操作与终止操作
     * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理!而在终止操作时一次性全部处理,称为“惰性求值”。
     */
    @Test
    public void IntermediateOperateWithStream(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );
         //创建流
        Stream<Employee> employeeStream = employees.stream();


        //中间操作，遇到终止操作之前，中间操作不会执行任何的处理
        Stream<Employee> filterStream = employeeStream.filter(employee -> {
            System.out.println("中间操作");
            return employee.getAge() > 18;
        });

    }

    /**
     * stream流中间操作与终止操作
     * 惰性求值: 多个中间操作可以连接起来形成一个流水线流,在中间处理过程中，只是对操作进行了记录，并不会立即执行,
     * 除非流水线上触发终止操作，否则中间操作不会执行任何的处理!需要等到执行终止操作的时候才会进行实际的计算,,称为“惰性求值”。
     */
    @Test
    public void IntermediateOperateWithStream2(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );
        //创建流
        Stream<Employee> employeeStream = employees.stream();


        //中间操作
        Stream<Employee> filterStream = employeeStream.filter(employee -> {
            System.out.println("中间操作");
            return employee.getAge() > 18;
        });

        //终止操作
        filterStream.forEach(System.out::println);//等价于filterStream.forEach(employee->System.out.println(employee));

    }


    /**
     *     limit(n)：获取n个元素
     */
    @Test
    public void testLimit(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );
        //创建流
        Stream<Employee> employeeStream = employees.stream();

        //中间操作
        Stream<Employee> filterStream = employeeStream.filter(employee ->
             employee.getAge() > 18
        ).limit(2);

        //终止操作
        filterStream.forEach(System.out::println);//等价于filterStream.forEach(employee->System.out.println(employee));
    }


    /**
     *  skip(n)：跳过n元素
     */
    @Test
    public void testSkip(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88),
                new Employee("张三2", 32, 9999.99),
                new Employee("李四2", 43, 5555.55),
                new Employee("王五2", 53, 3333.33),
                new Employee("赵六2", 55, 6666.66),
                new Employee("田七2", 66, 8888.88)
        );
        //创建流
        Stream<Employee> employeeStream = employees.stream();

        //中间操作
        Stream<Employee> skipStream = employeeStream.skip(3);
        Stream<Employee> filterStream = skipStream.filter(employee ->
                employee.getAge() > 18
        );

        //终止操作
        filterStream.forEach(System.out::println);//等价于filterStream.forEach(employee->System.out.println(employee));
    }


    /**
     * skip(m)配合limit(n)可实现分页
     */
    @Test
    public void testSkipAndLimit(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三1", 18, 9999.99),
                new Employee("李四2", 58, 5555.55),
                new Employee("王五3", 26, 3333.33),
                new Employee("赵六4", 36, 6666.66),
                new Employee("田七5", 12, 8888.88),
                new Employee("张三6", 32, 9999.99),
                new Employee("李四7", 43, 5555.55),
                new Employee("王五8", 53, 3333.33),
                new Employee("赵六9", 55, 6666.66),
                new Employee("田七10", 66, 8888.88),
                new Employee("黄11", 77, 8888.88)
        );
        //创建流
        Stream<Employee> employeeStream = employees.stream();

        //中间操作
        Stream<Employee> skipStream = employeeStream.skip(5);

        //终止操作
        Stream<Employee> limitStream = skipStream.limit(5);
        limitStream.forEach(System.out::println);//等价于filterStream.forEach(employee->System.out.println(employee));
    }


    /**
     * distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素，要想实现成功，必须实体类实现重写这两个方法！
     */
    @Test
    public void testDistinct(){
        Stream<Integer> integerStream = Stream.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        Stream<Integer> distinctStream = integerStream.distinct();
        distinctStream.forEach(System.out::println);
    }


    /**
     * distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素，要想实现成功，必须实体类实现重写这两个方法！
     * Employee没有重写 hashCode() 和 equals()方法，所以比较的是地址
     */
    @Test
    public void testDistinct2(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三1", 18, 9999.99),
                new Employee("张三1", 18, 9999.99),
                new Employee("张三2", 18, 9999.99)
        );

        long count = employees.stream().distinct().count();
        System.out.println(count);

    }



    /**
     * distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素，要想实现成功，必须实体类实现重写这两个方法！
     * Employee2重写 hashCode() 和 equals()方法，所以比较的是对象内容
     */
    @Test
    public void testDistinct3(){
        List<Employee2> employees = Arrays.asList(
                new Employee2("张三1", 18, 9999.99),
                new Employee2("张三1", 18, 9999.99),
                new Employee2("张三2", 18, 9999.99)
        );

        long count = employees.stream().distinct().count();
        System.out.println(count);
    }

    /**
     * map：接收Lambda ,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testMap(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff");

        Stream<String> mapStream = list.stream().map(str -> str.toUpperCase(Locale.ROOT));
        mapStream.forEach(System.out::println);
    }


    /**
     * map：接收Lambda ,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testMap2(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );

        //包含name的stream
        Stream<String> stringStream = employees.stream().map(employee -> employee.getName());
        stringStream.forEach(System.out::println);

        System.out.println();
        //接口方法的参数比引用方法的参数多一个， 接口方法的第一个参数恰巧是调用引用方法的对象（其引用方法所在类或其父类的实例,因此可以使用类名::实例方法
        Stream<String> stringStream2 = employees.stream().map(Employee::getName);
        stringStream2.forEach(System.out::println);
    }



    @Test
    public void testMap3() {
        List<String> list = Arrays.asList("aa", "bb");
        //list.stream()返回只是Stream<String>,  然后经过map,流中的每个String转化成了Stream<Character>，所以最后的返回值 是Stream<Stream<Character>>
        //假设流的标记是{}, 那么流中流就是：{ {aa}, {bb} }
        Stream<Stream<Character>> streams = list.stream().map(str -> filterCharacter(str));
        //stream中包含一系列的stream, 可以想象成外流套内流，外流是Stream<Stream<Character>>类型，内流是Stream<Character>类型
        streams.forEach(stream->stream.forEach(System.out::println));

        //简写：
        list.stream().map(str -> filterCharacter(str)).forEach(System.out::println);
    }

    /**
     * 把字符串转成对应的流Stream<Character>, 这里假设是aa--->{aa}
     * @param str
     * @return
     */
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * map：接收Lambda ,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("aa", "bb");
        //list.stream()返回只是Stream<String>,  然后经过flatMap,将流中的每个值都换成另一个流，然后把所有流连接成一个流，
        // 个人理解 flatMap完成了Stream<Stream<Character>>到<Stream<Character>>的转换，也就是把众多的流合并成了一个流，{ {aa}, {bb} }------->{aa,bb}
        Stream<Character> streams = list.stream().flatMap(str -> filterCharacter(str));
        //stream中包含一系列的stream, 可以想象成外流套内流，齐总的外流是Stream<Stream<Character>>类型，内流是Stream<Character>类型
        streams.forEach(System.out::println);

        //简写：
        System.out.println();
        list.stream().flatMap(str -> filterCharacter(str)).forEach(System.out::println);


    }

}



 class Employee {

    private String name;
    private int age;
    private  double salary;

    public Employee() {
    }

     public Employee(String name, int age, double salary) {
         this.name = name;
         this.age = age;
         this.salary = salary;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public double getSalary() {
         return salary;
     }

     public void setSalary(double salary) {
         this.salary = salary;
     }

     @Override
     public String toString() {
         return "Employee{" +
                 "name='" + name + '\'' +
                 ", age=" + age +
                 ", salary=" + salary +
                 '}';
     }

     /**
      * sorted()：自然排序，流中元素需实现Comparable接口
      * public class Students3 implements Comparable<Students3>
      */
     @Test
     public void testSort() {
         List<Students3> students = Arrays.asList(
                 new Students3("tom", 12),
                 new Students3("ashly", 14));

         Stream<Students3> sortedStream = students.stream().sorted();
         sortedStream.forEach(System.out::println);
     }


     /**
      * sorted(Comparator com)：定制排序，自定义Comparator排序器
      */
     @Test
     public void testSorted() {
         List<Employee> employees = Arrays.asList(
                 new Employee("mary", 18, 9999.99),
                 new Employee("alex", 58, 5555.55),
                 new Employee("tom", 26, 3333.33),
                 new Employee("ashly", 36, 6666.66),
                 new Employee("sherry", 12, 8888.88)
         );

         Stream<Employee> sortedStream = employees.stream().sorted((o1, o2) -> {
             if (o1.getName().equals(o2.getName())) {
                 return o1.getAge() - o2.getAge();
             } else {
                 return o1.getName().compareTo(o2.getName());
             }
         });
         sortedStream.forEach(System.out::println);
     }

 }



class Employee2 {

    private String name;
    private int age;
    private  double salary;

    public Employee2() {
    }

    public Employee2(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee2 employee2 = (Employee2) o;
        return age == employee2.age && Double.compare(employee2.salary, salary) == 0 && Objects.equals(name, employee2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }
}