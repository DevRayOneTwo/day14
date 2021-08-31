package ru.devray.day14.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CalculaterStepDefs {

    static Calculator calculator;
    int result;

    @Given("Caluclator object is created")
    public void caluclator_object_is_created() {
        calculator = new Calculator();
    }

    @When("adding {int} with {int}")
    public void adding_with(int i1, int i2) {
        result = calculator.add(i1, i2);
    }

    @Then("method returns some {int}")
    @Then("method returns {int}")
    public void method_returns(Integer int1) {
        Assertions.assertEquals(int1, result);
    }

}
