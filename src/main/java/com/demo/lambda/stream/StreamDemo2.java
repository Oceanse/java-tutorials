package com.demo.lambda.stream;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamDemo2 {


    /**
     * 流的终止操作: 匹配、查找、聚合操作
     * allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
     * noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
     * anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
     * findFirst：返回流中第一个元素
     * findAny：返回流中的任意元素
     * count：返回流中元素的总个数
     * max：返回流中元素最大值
     * min：返回流中元素最小值
     */
    @Test
    public void testMatchFindAggregation() {
        //匹配
        List<String> ls = Arrays.asList("a", "a", "c", "a", "b");
        boolean aa = ls.stream().anyMatch(str -> str.equals("a"));
        boolean bb = ls.stream().allMatch(str -> str.equals("a"));
        boolean cc = ls.stream().noneMatch(str -> str.equals("a"));
        boolean dd = ls.stream().noneMatch(str -> str.equals("e"));
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(dd);// TRUE

        //查找
        String findFirst = ls.stream().findFirst().get();
        String findAny = ls.stream().findAny().get();
        System.out.println("findAny = " + findAny);
        System.out.println("findFirst = " + findFirst);

        //聚合
        long count = ls.stream().filter(str -> str.equals("a")).count();//返回满足筛选条件的元素的数量
        Optional<String> maxOptional = ls.stream().max(String::compareTo);//等价于max((str1, str2) -> str1.compareTo(str2))
        String max = maxOptional.get();
        Optional<String> minOptional = ls.stream().min(String::compareTo);//等价于min((str1, str2) -> str1.compareTo(str2))
        String min = minOptional.get();
        System.out.println("count = " + count);
        System.out.println("min = " + min);
        System.out.println("max = " + max);
    }


    @Test
    public void testAggregation() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );
        Employee employeeOfMaxSalary = employees.stream().max((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary())).get();
        System.out.println("employeeOfMaxSalary = " + employeeOfMaxSalary);
        Employee employeeOfMaxSalary2 = employees.stream().max((e1, e2) -> Double.valueOf(e1.getSalary()).compareTo(e2.getSalary())).get();
        System.out.println("employeeOfMaxSalary2 = " + employeeOfMaxSalary2);

        Double maxSalary = employees.stream().map(e -> e.getSalary()).max((d1, d2) -> d1.compareTo(d2)).get();
        System.out.println("maxSalary = " + maxSalary);
       Double maxSalary2 = employees.stream().map(employee -> employee.getSalary()).max(Double::compareTo).get();
        System.out.println("maxSalary2 = " + maxSalary2);

       Double maxSalary3 = employees.stream().map(employee -> employee.getSalary()).max((d1,d2)->Double.compare(d1,d2)).get();
        System.out.println("maxSalary3 = " + maxSalary3);
       Double maxSalary4 = employees.stream().map(employee -> employee.getSalary()).max(Double::compare).get();
        System.out.println("maxSalary4 = " + maxSalary4);
    }


    @Test
    public void testFind() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("tom", 26, 3333.33),
                new Employee("alex", 58, 5555.55),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        Optional<Employee> anyOption = employees.parallelStream().findAny();
        Employee employee = anyOption.get();//并行流返回结果不确定
        System.out.println(employee);
    }


    /**
     * 归约
     * Optional<T> reduce(BinaryOperator<T> accumulator)：
     * 第一次执行时，accumulator函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
     * 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推。
     * 简单理解为：可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void testReduce() {
        // 求和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //第一次执行时，x1=1,x2=2, 执行后结果=3；
        //第二次执行时，x1=3,x2=3, 执行后结果=6；
        //第三次执行时，x1=4,x2=4, 执行后结果=10；
        //第四次执行时，x1=10,x2=5, 执行后结果=15；
        Optional<Integer> reduceOptional = list.stream().reduce((x1, x2) -> x1 + x2);
        Integer sum = reduceOptional.get();
        System.out.println("sum = " + sum);

        // 方法引用
        Optional<Integer> reduceOptional2 = list.stream().reduce(Integer::sum);
        Integer sum2 = reduceOptional2.get();
        System.out.println("sum2 = " + sum2);
    }


    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator)：
     * 第一次执行时，accumulator函数的第一个参数为流中的第一个元素，第二个参数为流中的第二个元素；
     * 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推。
     * 简单理解为：可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void testReduce2() {
        // 求和
        List<Integer> list = Arrays.asList();
        //没有初始值，可能为空，所以返回 Optional 对象
        Optional<Integer> reduceOptional = list.stream().reduce((x1, x2) -> x1 + x2);
        Integer sum = reduceOptional.get();
        System.out.println("sum = " + sum);
    }


    @Test
    public void testReduce3() {
        // 求和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //这里返回值是Integer，是因为有起始值0
        //第一次执行时,x1=0(起始值),x2=1(流中的第一个元素)
        //第二次执行时，x1=第一次函数执行的结果，x2=流中的第二个元素；依次类推。
        Integer sum = list.stream().reduce(0, (x1, x2) -> x1 + x2);
        System.out.println("sum = " + sum);

        // 方法引用
        Integer sum2 = list.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
    }


    /**
     * map和reduce的连接通常被成为map-reduce模式，因Google用它来进行网络搜索而出名
     */
    @Test
    public void testReduce4() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        Double totalSalary = employees.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
        double averageSalary = totalSalary / employees.stream().count();
        System.out.println("totalSalary = " + totalSalary);
        System.out.println("averageSalary = " + averageSalary);
    }


    /**
     * map-reduce实现集合数量运算
     */
    @Test
    public void testReduce5() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        Integer count = employees.stream().map(employee -> 1).reduce(0,Integer::sum);
        System.out.println("count = " + count);
    }
    

    /**
     * collect：将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     * Collector<T, A, R> 是一个接口，有以下5个抽象方法：
     * Supplier<A> supplier()：创建一个结果容器A
     * BiConsumer<A, T> accumulator()：消费型接口，第一个参数为容器A，第二个参数为流中元素T。
     * BinaryOperator<A> combiner()：函数接口，该参数的作用跟上一个方法(reduce)中的combiner参数一样，将并行流中各                                                                 个子进程的运行结果(accumulator函数操作后的容器A)进行合并。
     * Function<A, R> finisher()：函数式接口，参数为：容器A，返回类型为：collect方法最终想要的结果R。
     * Set<Characteristics> characteristics()：返回一个不可变的Set集合，用来表明该Collector的特征。有以下三个特征：
     * CONCURRENT：表示此收集器支持并发。（官方文档还有其他描述，暂时没去探索，故不作过多翻译）
     * UNORDERED：表示该收集操作不会保留流中元素原有的顺序。
     * IDENTITY_FINISH：表示finisher参数只是标识而已，可忽略。
     */
    @Test
    public void testCollect() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        Stream<String> stringStream = employees.stream().map(employee -> employee.getName());
        List<String> nameList = stringStream.collect(Collectors.toList());
        System.out.println("nameList = " + nameList);

        //方法引用
        List<String> nameList2 = employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println("nameList2 = " + nameList2);
    }

    @Test
    public void testCollect2() {
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 6666.66),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        //放入List
        List<Double> salaryList = employees.stream().map(Employee::getSalary).collect(Collectors.toList());
        System.out.println("salaryList = " + salaryList);

        //放入set可以进行去重
        Set<Double> salarySet = employees.stream().map(Employee::getSalary).collect(Collectors.toSet());
        System.out.println("salarySet = " + salarySet);

        //转成map,注:key不能相同，否则报错
        Map<String, Double> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println("employeeMap = " + employeeMap);

         //要想放入我们指定的收集器中，可以采取以下方式
        // Collectors.toCollection( xxx ::new) xxx为我们想要放入的集合类型
        HashSet salaryHashSet = employees.stream().map(Employee::getSalary).collect(Collectors.toCollection(HashSet::new));
        System.out.println("salaryHashSet = " + salaryHashSet);
    }



    @Test
    public void testCollect3(){
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );

        //总数
        Long count = employees.stream().collect(Collectors.counting());
        long count1 = employees.stream().count();
        System.out.println("count = " + count);
        System.out.println("count1 = " + count1);

        //平均值
        Double averageSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("averageSalary = " + averageSalary);

        //总和
        int ageSum = employees.stream().collect(Collectors.summingInt(Employee::getAge));
        System.out.println("ageSum = " + ageSum);

        //最小值：最小的salary
        Optional<Double> minSalary = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println("minSalary = " + minSalary.get());
    }

    /**
     * 分组
     */
    @Test
    public void testGroup(){
        List<Employee3> employees = Arrays.asList(
                new Employee3("mary", "junior","研发"),
                new Employee3("alex", "senior","研发"),
                new Employee3("tom", "senior","测试"),
                new Employee3("ashly","junior", "测试"),
                new Employee3("sherry","junior", "运维"),
                new Employee3("nancy","senior", "运维")
        );
        //按照部门分组
        Map<String, List<Employee3>> groupBtDepartment = employees.stream().collect(Collectors.groupingBy(Employee3::getDepartment));
        System.out.println("groupBtDepartment = " + groupBtDepartment);
    }




    /**
     * 多级分组
     */
    @Test
    public void testGroup2(){
        List<Employee3> employees = Arrays.asList(
                new Employee3("mary", "junior","研发"),
                new Employee3("alex", "senior","研发"),
                new Employee3("tom", "senior","测试"),
                new Employee3("ashly","junior", "测试"),
                new Employee3("sherry","junior", "运维"),
                new Employee3("nancy","senior", "运维")
        );

        //先按照部门分，部门相同再按照职位分
        Map<String, Map<String, List<Employee3>>> groupBtDepartmentAndRole = employees.stream().collect(Collectors.groupingBy(Employee3::getDepartment, Collectors.groupingBy(Employee3::getRole)));
        System.out.println("groupBtDepartmentAndRole = " + groupBtDepartmentAndRole);
    }


    /**
     * 对数据进行统计
     */
    @Test
    public void testStatistic(){
        List<Employee> employees = Arrays.asList(
                new Employee("mary", 18, 9999.99),
                new Employee("alex", 58, 5555.55),
                new Employee("tom", 26, 3333.33),
                new Employee("ashly", 36, 6666.66),
                new Employee("sherry", 12, 8888.88)
        );
        DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getCount());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());

    }
}


 class Employee3 {

    private String name;
    private String role;
    private String department;

    public Employee3() {
    }


    public Employee3(String name, String role, String department) {
        this.name = name;
        this.role = role;
        this.department = department;
    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getRole() {
         return role;
     }

     public void setRole(String role) {
         this.role = role;
     }

     public String getDepartment() {
         return department;
     }

     public void setDepartment(String department) {
         this.department = department;
     }

     @Override
     public String toString() {
         return "Employee3{" +
                 "name='" + name + '\'' +
                 ", role='" + role + '\'' +
                 ", department='" + department + '\'' +
                 '}';
     }
 }