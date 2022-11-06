package com.demo.basic.array;

import com.demo.basic.code_block.construct_block.demo1.Person;
import com.demo.collection_map.model.comparable_model.Students;
import com.demo.collection_map.model.comparable_model.Students3;
import com.demo.collection_map.model.comparator_model.Students4;
import com.demo.collection_map.model.comparator_model.Students4Comparator;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * Arrays类处于java.util包下， 为了在程序中使用Arrays类， 必须在程序中导入java.util.Arrays类。
 */
public class ArraysTool {

    /**
     * Arrays.toString遍历
     */
    @Test
    public void test1() {
        boolean[] b = new boolean[]{true, false, true};
        System.out.println(Arrays.toString(b));
    }

    @Test
    public void test1_1() {
        boolean[] b = new boolean[]{true, false, true};
        System.out.println(myArraysToString(b));
    }


    public String myArraysToString(boolean[] array) {
        if (array == null) {
            return null;
        }

        int length = array.length;
        if (length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length - 1; i++) {
            sb.append(array[i]);
            sb.append(",");
        }
        //单独添加最后一个元素
        sb.append(array[length - 1]);
        sb.append("]");

        return sb.toString();
    }

    /**
     * Stream后遍历
     */
    @Test
    public void test1_2() {
        double[] score = {100, 110, 120};

        //Arrays.stream  数组stream
        Arrays.stream(score).forEach(item -> System.out.println(item));
    }


    /**
     * Array--->List遍历
     * public static <T> List<T> asList(T... a)
     */
    @Test
    public void test1_3() {
        //传入数组对象
        List<Person> personList = Arrays.asList(new Person[]{new Person(), new Person()});
        System.out.println(personList);

        String[] fruit = new String[]{"apple", "banana", "grape"};
        //传入数组对象的引用
        List<String> fruits = Arrays.asList(fruit);
        System.out.println(fruits);

        //传入可变参数
        List<String> vegetables = Arrays.asList("tomato", "mushroom", "papper");
        System.out.println(vegetables);
    }


    /**
     * 数组排序
     * Arrays.sort
     */
    @Test
    public void testSort() {
        int[] ii = new int[]{3, 1, 8, 2};
        System.out.println(Arrays.toString(ii));
        Arrays.sort(ii);
        System.out.println(Arrays.toString(ii));
    }


    /**
     * 当我们想排序一个自定义的数组，Java又没有直接提供它们的比较方式时,
     * 我们可以通过实现接口Comparable并重写compareTo()方法来添加对自定义数组的比较，
     * 然后通过Arrays的sort（）方法来将这个数组排序。
     */
    @Test
    public void testSort2() {
        //students3实现接口Comparable并重写compareTo()方法
        Students3[] students3 = {new Students3("张三", 13),
                new Students3("张三", 12),
                new Students3("张三", 14)};

        System.out.println(Arrays.toString(students3));
        Arrays.sort(students3);
        System.out.println(Arrays.toString(students3));
    }





    /**
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * public static <T> void sort(T[] a, Comparator<? super T> c)
     */
    @Test
    public void testSort3() {
        //students3实现接口Comparable并重写compareTo()方法
        Students4[] students4 = {new Students4("张三", 13),
                new Students4("张三", 12),
                new Students4("张三", 14)};

        System.out.println(Arrays.toString(students4));
        //这里传入一个比较器参数
        Arrays.sort(students4, new Students4Comparator());
        System.out.println(Arrays.toString(students4));
    }


    /**
     * 通过匿名内部类创建比较器参数
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * public static <T> void sort(T[] a, Comparator<? super T> c)
     */
    @Test
    public void testSort4() {
        //students3实现接口Comparable并重写compareTo()方法
        Students[] students = {new Students("张三", 13),
                new Students("张三", 12),
                new Students("张三", 14)};

        System.out.println(Arrays.toString(students));
        //通过匿名内部类创建比较器参数
        Arrays.sort(students, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(Arrays.toString(students));
    }


    /**
     * 通过lambda表达式创建比较器参数
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * public static <T> void sort(T[] a, Comparator<? super T> c)
     */
    @Test
    public void testSort5() {
        //students3实现接口Comparable并重写compareTo()方法
        Students[] students = {new Students("张三", 13),
                new Students("张三", 12),
                new Students("张三", 14)};

        System.out.println(Arrays.toString(students));
        //通过匿名内部类创建比较器参数
        Arrays.sort(students, ( o1,  o2) -> {
                    return o1.getAge() - o2.getAge();
                }
        );
        System.out.println(Arrays.toString(students));
    }


    /**
     * 通过lambda表达式创建比较器参数
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * public static <T> void sort(T[] a, Comparator<? super T> c)
     */
    @Test
    public void testSort6() {
        //students3实现接口Comparable并重写compareTo()方法
        Students[] students = {new Students("张三", 13),
                new Students("张三", 12),
                new Students("张三", 14)};
        System.out.println(Arrays.toString(students));
        //通过匿名内部类创建比较器参数
        Arrays.sort(students, Comparator.comparingInt(Students::getAge)
        );
        System.out.println(Arrays.toString(students));
    }


    /**
     * Arrays.equals会把两个数组相同索引的的一对元素进行equals比较
     * 如果是基本类型数组，会逐个比较每个元素的字面值
     * 如果是引用类型数组，要看引用类型是否对equals进行了重写，如果进行了重写，比较的是两个元素指向对象的内容；
     * 如果没有进行重写，比较的是两个元素指向对象的地址
     */
    @Test
    public void test3() {
        double[] scores = new double[]{1, 2, 3, 4, 5, 6};
        double[] scores2 = new double[]{1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.equals(scores, scores2));
        System.out.println(myDoubleEquals(scores, scores2));
        System.out.println();

        char[] c1 = {'a', 'b'};
        char[] c2 = {97, 'b'};
        System.out.println(Arrays.equals(c1, c2));
        System.out.println(myCharEquals(c1, c2));
        System.out.println();

        Double[] scores3 = new Double[]{new Double(1), new Double(2)};
        Double[] scores4 = new Double[]{new Double(1), new Double(2)};
        System.out.println(Arrays.equals(scores3, scores4));
        System.out.println(myObjectEquals(scores3, scores4));
        System.out.println();

        Person[] personList = new Person[]{new Person("ocean", 31)};
        Person[] personList2 = new Person[]{new Person("ocean", 31)};
        System.out.println(Arrays.equals(personList, personList2));
        System.out.println(myObjectEquals(personList, personList2));
    }

    public boolean myDoubleEquals(double[] array1, double[] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            //只要有一对对应的数组元素不同,那么两个数组不同，返回false
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean myCharEquals(char[] array1, char[] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            //只要有一对对应的数组元素不同,那么两个数组不同，返回false
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean myObjectEquals(Object[] array1, Object[] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            //一个元素为null, 另一个同索引的元素不为null, 那么两个数组不同，返回false
            if (array1[i] == null) {
                if (array2[i] != null) {
                    return false;
                }
            } else {
                //只要有一对对应的数组元素不同,那么两个数组不同，返回false;这里是通过equals方法比较两个对象是否相等
                if (!array1[i].equals(array2[i])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 数组拷贝
     * System.arraycopy
     */
    @Test
    public void test4() {

        //数组复制
        //names存储着首个字符串常量池中的地址
        String[] names = new String[]{"Apple", "Huawei", "Xiaomi"};
        String[] names2 = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            //把原数组中的变量值(每个字符串常量池中的地址)赋值给新的变量，新的数组也会保存着原数组中的对应的字符串常量池中的地址
            names2[i] = names[i];
        }
        System.out.println("names2:" + Arrays.toString(names2));


        /*
        arrayCopy( arr1, 2, arr2, 5, 10);
        意思是;将arr1数组里从索引为2的元素开始, 复制到数组arr2里的索引为5的位置, 复制的元素个数为10个.
        */
        String[] names3 = new String[names.length];
        System.arraycopy(names, 0, names3, 0, names.length);
        System.out.println("names3:" + Arrays.toString(names3));
    }


    /**
     * 数组拷贝
     * public static <T> T[] copyOf(T[] original, int newLength)
     * copyOf(原数组，新数组的长度)
     */
    @Test
    public void test4_2() {
        String[] names = new String[]{"ocean", "mama", "zhuxiang"};
        String[] newNames = Arrays.copyOf(names, 3);
        String[] newNames2 = Arrays.copyOf(names, 5);
        System.out.println(Arrays.toString(newNames));
        System.out.println(Arrays.toString(newNames2));
    }


    /**
     * 数组拷贝
     * public static <T> T[] copyOf(T[] original, int newLength)
     */
    @Test
    public void test4_3() {
        String[] names = new String[]{"ocean", "mama", "zhuxiang"};
        //数组扩容，本质是创建了一个长度是5的新的数组，并把旧数组的元素拷贝到新的数组
        names = Arrays.copyOf(names, 5);
        System.out.println(Arrays.toString(names));
    }


    /**
     * 批量修改数组元素
     * void fil1(type[] a, type val): 该方法将会把a 数组的所有元素都赋值为val
     */
    @Test
    public void test5() {
        int[] array = new int[]{3, 1, 8, 2};
        System.out.println(Arrays.toString(array));
        Arrays.fill(array, 0);
        System.out.println(Arrays.toString(array));
    }


    /**
     * void fill(type[] a, int fromIndex, int toIndex, type val):
     * 该方法仅仅将a数组的fromlndex到toindex-1索引的数组元素赋值为val。
     * 也就是只替换[fromIndex, toIndex)
     */
    @Test
    public void test5_2() {
        int[] array = new int[]{3, 1, 8, 2};
        System.out.println(Arrays.toString(array));
        Arrays.fill(array, 0, 2, 100);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test6() {
        int[] array = new int[]{3, 1, 8, 2};
        int[] copy = array.clone();
        System.out.println(array);
        System.out.println(copy);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(copy));
    }
}
