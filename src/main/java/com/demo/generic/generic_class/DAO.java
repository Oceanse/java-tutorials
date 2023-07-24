package com.demo.generic.generic_class;

import java.util.*;


/**
 * 类结构是面向对象中最基本的元素，如果我们的类需要有很好的扩展性，那么我们可以将其设置成泛型的。
 * 假设我们需要一个数据的容器类，通过传入不同类型的数据，可以存储处理相应类型的数据。
 * @param <T>
 */
public class DAO<T> {

    /**
     * 保存所有信息,这里也是使用类泛型的属性
     */
    private Map<String,T> map=new HashMap<>();

    /**
     * 添加T到map中
     * @param id
     * @param entity
     */
    public void save(String id, T entity){
        map.put(id,entity);
    }

    /**
     * 根据id查询T
     * @param id
     * @return
     */
    public T getEntity(String id){
        return map.get(id);
    }

    /**
     * 返回map中所有T的集合，这里也是使用类泛型的方法
     * @return
     */
    public List<T> getEntities(){
        ArrayList<T> list =new ArrayList<>();
        Collection<T> values = map.values();
        for (T entity : values) {
            list.add(entity);
        }
        return list;
    }

    /**
     * 根据id,更新map
     * @param id
     * @param entity
     */
    public void update(String id, T entity){
        if(map.containsKey(id)){
            map.put(id,entity);
        }
    }


    /**
     * 根据id删除entry
     * @param id
     */
    public void delete(String id){
        map.remove(id);
    }

    public static void main(String[] args) {
        DAO<User> dao=new DAO<>();
        dao.save("1001",new User("ocean",33));
        dao.save("1002",new User("mxz",57));
        dao.save("1003",new User("pza",67));
        dao.save("1003",new User("pgp",54));

        List<User> entities = dao.getEntities();
        System.out.println("entities = " + entities);

        dao.update("1003",new User("mxz",99));
        User entity1003 = dao.getEntity("1003");
        System.out.println("entity1003 = " + entity1003);

    }
}


class User{
    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}