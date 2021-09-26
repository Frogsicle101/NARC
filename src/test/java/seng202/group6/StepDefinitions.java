package seng202.group6;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepDefinitions {

    int number;

    @Given("my favourite number is {int}")
    public void my_favourite_number_is(Integer number) {
        this.number = number;
    }

    @Then("the number is {int}")
    public void the_number_is(Integer number) {
        assertEquals(this.number, number);
    }

}