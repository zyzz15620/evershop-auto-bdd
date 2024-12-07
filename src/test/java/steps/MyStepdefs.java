package steps;

import io.cucumber.java.en.Given;

public class MyStepdefs {
    @Given("The cat eat the food")
    public void theCatEatTheFood() {
        System.out.println("yum yum");
    }
}
