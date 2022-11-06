package com.demo.json_parse.jackson.jsonnode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetJsonNodeField {


    /**
     * @throws JsonProcessingException
     */
    @Test
    public void test() throws JsonProcessingException {
        String jsonStr = "{\n" +
                "               \"name\":\"ocean\",\n" +
                "               \"age\":28,\n" +
                "               \"score\":{\"chinese\":100,\"math\":120}\n" +
                "            }";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonStr);

        //even if the two fields are String fields, the get() method always returns a JsonNode to represent the field.
        JsonNode field1 = rootNode.get("name");
        JsonNode field2 = rootNode.get("age");
        JsonNode field3 = rootNode.get("score");
        JsonNode field4 = rootNode.get("xx");//返回null

        System.out.println(field1);
        System.out.println(field2);
        System.out.println(field3);
        System.out.println(field4);
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = (ObjectNode)mapper.readTree("{}");
        rootNode.put("name","ocean");
        System.out.println(rootNode);



    }

    /**
     *path(String) similar to get(String)
     * @throws JsonProcessingException
     */
    @Test
    public void test2() throws JsonProcessingException {
        String jsonStr = "{\n" +
                "               \"name\":\"ocean\",\n" +
                "               \"age\":28,\n" +
                "               \"score\":{\"chinese\":100,\"math\":120}\n" +
                "            }";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonStr);

        //even if the two fields are String fields, the path() method always returns a JsonNode_Object_convert to represent the field.
        JsonNode field1 = rootNode.path("name");
        JsonNode field2 = rootNode.path("age");
        JsonNode field3 = rootNode.path("score");
        JsonNode field4 = rootNode.path("xx");

        System.out.println(field1);
        System.out.println(field2);
        System.out.println(field3);
        System.out.println(field4);
    }


    @Test
    public void test3() throws IOException {
        Path path = Paths.get("suite.json");
        String suiteContent = new String(Files.readAllBytes(path));
        System.out.println("suiteContent:  " + suiteContent);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode state = objectMapper.readTree(suiteContent).get("testcases").get("1").get("state");
        JsonNode state2 = objectMapper.readTree(suiteContent).path("testcases").path("1").path("state");
        System.out.println(state);
        System.out.println(state2);
    }








    /**
     * The Jackson JsonNode_Object_convert has a special method called at() .
     * The at() method can access a JSON field from anywhere in the JSON graph which the given JsonNode_Object_convert is the root of.
     * {
     *   "identification" :  {
     *         "name" : "James",
     *         "ssn": "ABC123552"
     *     }
     * }
     * @throws JsonProcessingException
     */
    @Test
    public void test4() throws JsonProcessingException {
        String json = "{\n" +
                "  \"identification\" :  {\n" +
                "        \"name\" : \"James\",\n" +
                "        \"ssn\" : \"ABC123552\"\n" +
                "    }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        //This is a JSON path expression.
        // This path expression specifies the complete path from the root JsonNode_Object_convert and down to the field you want
        // to access the value of. This is similar to the path to a file from the root of the file system down to the
        //file in a Unix file system.
        //Notice the JSON path expression must start with a slash character ( the / character).
        JsonNode nameNode = rootNode.at("/identification/name");//必须以斜杠开始
        System.out.println(nameNode);
        System.out.println();
        System.out.println(rootNode.at(""));//获取rootnode
    }



    @Test
    public void test4_2() throws JsonProcessingException {
        String json ="{\n" +
                "  \"name\": \"ocean\"\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        //This is a JSON path expression.
        // This path expression specifies the complete path from the root JsonNode_Object_convert and down to the field you want
        // to access the value of. This is similar to the path to a file from the root of the file system down to the
        //file in a Unix file system.
        //Notice the JSON path expression must start with a slash character ( the / character).
        System.out.println(rootNode.at("/*"));//获取rootnode
        //JsonNode stpNode = rootNode.at("/resources/stp");//必须以斜杠开始
        //System.out.println(stpNode);
    }


    @Test
    public void test5() throws JsonProcessingException {

            String jsonStr = "{\"name\":null}";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonStr);
            System.out.println(rootNode.hasNonNull("name"));
        System.out.println(rootNode.hasNonNull("sex"));
    }

}
