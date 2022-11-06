package com.demo.collection_map.map;

import com.demo.collection_map.model.Apple;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.annotations.Test;

import java.util.*;

/**
 * 源码分析：https://blog.csdn.net/u010648555/article/details/60324303
 *
 * HashMap底层实现原理解析:
 * https://www.cnblogs.com/shenjiangwei/p/13732568.html?share_token=bb29ed22-f3b9-40ab-9d84-5b04a534a791
 * https://blog.csdn.net/weixin_48655626/article/details/110207299?share_token=fdf96772-d702-498f-9337-bb15cc70feea
 *
 *
 * @author epanhai
 */
public class HashMapTest {

    /**
     * 添加
     */
    @Test
    public void add() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "girl");
        //后面的键值会覆盖前面的键值
        hm.put("sex", "man");
        //key和value允许为null
        hm.put(null, null);
        System.out.println(hm);
    }

    @Test
    public void add2() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "girl");

        HashMap hm2 = new HashMap();
        hm.put("major", "software engineer");
        hm2.putAll(hm);
        System.out.println(hm2);
    }


    @Test
    public void add3() {
        HashMap hm = new HashMap();
        hm.putAll(null);

    }


    /**
     * 删除
     */
    @Test
    public void delete() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 31);
        hm.put("favoriteFruit", new Apple());
        hm.put("personality", "hesitate");

        //如果存在一个键的映射关系，则通过键值将其从此映射中移除
        Object personality = hm.remove("personality");
        System.out.println(personality);
        System.out.println(hm);

        //删除的键值对不存在会返回null
        Object personality2 = hm.remove("personality");
        System.out.println(personality2);
        System.out.println(hm);

        hm.clear();
        System.out.println("after clear--------------------------");
        System.out.println(hm);
        System.out.println(hm.size());
        System.out.println(hm.isEmpty());
    }


    /**
     * 修改
     */
    @Test
    public void modify() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 31);
        hm.put("favoriteFruit", new Apple());
        hm.put("personality", "hesitate");
        //覆盖修改
        hm.put("personality", "decisive");
        System.out.println(hm);
    }


    /**
     * 查询
     */
    @Test
    public void queryValueByKey() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 31);
        hm.put("favoriteFruit", new Apple());
        hm.put("personality", "decisive");

        //decisive，返回Object类型
        Object personality = hm.get("personality");
        //不存在对应的key则返回null
        Object degree = hm.get("degree");
        System.out.println("personality=" + personality);
        System.out.println("degree=" + degree);

        //使用泛型
        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("name", "ocean");
        //返回String
        String name = hm2.get("name");
        System.out.println("name=" + name);
    }


    /**
     * 获取所有的key
     */
    @Test
    public void getAllKeys() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");

        //Map 中的 key 用Set来存放，不允许重复;
        Set infos = hm.keySet();
        for (Object info : infos) {
            System.out.print(info + " ");
        }

        System.out.println();
        HashMap<String, Object> hm2 = new HashMap<>();
        hm2.put("hobby", new String[]{"music"});
        hm2.put("sex", "man");

        Set<String> infos2 = hm2.keySet();
        for (String info : infos2) {
            System.out.print(info + " ");
        }
    }


    /**
     * 获取所有的value
     */
    @Test
    public void getAllValues() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");

        //value用Collection存放
        System.out.print("map.values:");
        Collection c = hm.values();
        for (Object o : c) {
            System.out.print(o + " ");
        }
    }


    /**
     * 遍历map
     */
    @Test
    public void traverseMap1() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("hobby", new String[]{"music"});

        Set keys = hm.keySet();
        for (Object key : keys) {
            System.out.println(key + ": " + hm.get(key));
        }
    }


    /**
     * 遍历map
     */
    @Test
    public void traverseMap2() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");

        Set set = hm.entrySet();
        for (Object o : set) {
            Map.Entry mp = (Map.Entry) o;
            System.out.println(mp.getKey() + "--->" + mp.getValue());
        }
        System.out.println();

        Set<Map.Entry> set2 = hm.entrySet();
        for (Map.Entry entry : set2) {
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }


    /**
     * 遍历map
     */
    @Test
    public void traverseMap2_2() {
        //带有泛型
        HashMap<String, String> hm = new HashMap();
        hm.put("account", "1234");
        hm.put("balance", "1000");

        //HashMap使用泛型后这里才能用Map.Entry接收
        for (Map.Entry entry : hm.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        //HashMap使用泛型后这里才能用Map.Entr<String, String>y接收
        System.out.println();
        for (Map.Entry<String, String> entry : hm.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }


    /**
     * 遍历map
     */
    @Test
    public void traverseMap3_1() {
        //带有泛型
        HashMap<String, String> hm = new HashMap();
        hm.put("account", "1234");
        hm.put("balance", "1000");

        Set<Map.Entry<String, String>> set = hm.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "-->" + next.getValue());
        }
    }

    /**
     * 遍历map
     */
    @Test
    public void traverseMap3_2() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");

        Set<Map.Entry> set = hm.entrySet();
        Iterator<Map.Entry> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry next = iterator.next();
            System.out.println(next.getKey() + "-->" + next.getValue());
        }


    }

    /**
     * 遍历map
     */
    @Test
    public void traverseMap4() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");
        hm.forEach((k, v) -> System.out.println(k + ": " + v));
    }


    /**
     * 这里时浅拷贝，两个内容相同的对象
     */
    @Test
    public void cloneTest() {
        HashMap hm = new HashMap();
        hm.put("name", "ocean");
        hm.put("age", 28);
        hm.put("sex", "man");

        Object clone = hm.clone();
        System.out.println(hm);
        System.out.println(clone);
        //HashMap重写了equals方法
        System.out.println(hm.equals(clone));
        System.out.println(hm == clone);
    }


    @Test
    public void test() {
        Map map = new Hashtable();
        map.put("name", "ocean");
        map.put("age", "28");
        map.put("sex", "man");

        //键值对数量
        System.out.println(map.size());
        //是否为空
        System.out.println(map.isEmpty());
        //是否包含key
        System.out.println(map.containsKey("name"));
        //是否包含value
        System.out.println(map.containsValue("ocean"));

    }
}
