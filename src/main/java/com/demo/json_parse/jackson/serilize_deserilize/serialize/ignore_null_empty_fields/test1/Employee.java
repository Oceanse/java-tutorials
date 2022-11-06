package com.demo.json_parse.jackson.serilize_deserilize.serialize.ignore_null_empty_fields.test1;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Include.NON_NULL: Indicates that only properties with not null values will be included in JSON
 * Include.NON_EMPTY: Indicates that only properties that are not empty will be included in JSON
 */
public class Employee {
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String lastName;

    public Employee(int id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
