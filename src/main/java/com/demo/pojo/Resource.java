package com.demo.pojo;

import java.util.Objects;

/**
 * @author epanhai
 */
public class Resource {
    String name;
    String path;

    public Resource() {
    }

    public Resource(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        //Object o=new Resource(); 那么o instanceof Resource返回true
        if (o instanceof Resource) {
            Resource resource = (Resource) o;
            return this.name == resource.name &&
                    this.path == resource.path;
        }
        //Object o=new Person(); 那么o instanceof Resource返回false
        return false;
    }



    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }
}
