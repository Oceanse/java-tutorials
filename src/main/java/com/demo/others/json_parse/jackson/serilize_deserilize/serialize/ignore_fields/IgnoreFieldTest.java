package com.demo.others.json_parse.jackson.serilize_deserilize.serialize.ignore_fields;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IgnoreFieldTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CustomerDTO dtoObject = new CustomerDTO("CUST100", "Tony", "Stark");
        String dtoAsString = mapper.writeValueAsString(dtoObject);
        System.out.println(dtoAsString);
    }
}
