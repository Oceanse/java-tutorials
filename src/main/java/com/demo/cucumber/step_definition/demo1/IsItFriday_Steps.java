package com.demo.cucumber.step_definition.demo1;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;


class IsItFriday {

    static String isItFriday(String today) {
        return "Nope";
    }
}

public class IsItFriday_Steps {
    private String today;
    private String actualAnswer;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told \"Nope\"")
    public void i_should_be_told() {
        assertEquals("Nope", actualAnswer);
    }
}