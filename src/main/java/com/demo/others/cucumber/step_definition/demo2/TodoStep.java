package com.demo.others.cucumber.step_definition.demo2;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;


public class TodoStep {
    TodoList todo;

    @Given("^I have total (\\d+) tasks$")
    public void i_have_total_tasks(int totalTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        todo = new TodoList();
        assertEquals(todo.getTotalTaskCount(), totalTasks);
    }

    @When("^I have finished (\\d+) task$")
    public void i_have_finished_task(int finishedTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        todo.finishTask(finishedTasks);
    }

    @Then("^I left (\\d+) tasks that are not finished$")
    public void i_left_tasks_that_are_not_finished(int leftTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(todo.getRestTasksCount(), leftTasks);
    }
}
