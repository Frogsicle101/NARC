package seng202.group6;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import seng202.group6.Models.Crime;
import seng202.group6.Models.Frequency;
import seng202.group6.Services.RankService;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seng202.group6.Services.RankService.rankedTypeList;


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

    //First cucumber test for ranking a crime by crime type??
    ArrayList<Crime> crimeArrayList = new ArrayList<>();
    ArrayList<Frequency> expectedCrimeList = new ArrayList<>();

    @Given("the crime type {string}")
    public void theCrimeType(String crime) {
        Crime crime1 = new Crime();
        crime1.setPrimaryDescription(crime);
        crimeArrayList.add(crime1);
    }

    @When("I rank crimes")
    public void iRankCrimes() {
        expectedCrimeList = rankedTypeList(crimeArrayList);
    }

    @Then("the most common crime should be {string} with frequency value {int}")
    public void theMostCommonCrimeShouldBeWithFrequencyValue(String crimeName, Integer frequency) {
        Frequency freq = new Frequency(crimeName, frequency);
        assert(expectedCrimeList.get(0).equals(freq));
    }

}