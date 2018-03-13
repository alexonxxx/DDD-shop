package com.drpicox.dddshop;

import cucumber.api.java.en.Given;

public class PatataStepDefs {

    @Given("^a (\\w+) potatoe$")
    public void a_round_potatoe(String shape) throws Exception {
        System.out.println("A "+shape+" POTATOE");
    }
}
