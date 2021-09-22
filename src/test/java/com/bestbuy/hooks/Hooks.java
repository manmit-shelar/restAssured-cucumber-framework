package com.bestbuy.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {


    // Hooks in restAssured can be used to add records (@Before) and delete records(@After) from the DB.
    @Before("@addRecord")
    public void addRecord() throws Throwable {

    }
    
    @After("@deleteRecord")
    public void deleteRecord() throws Throwable {

    }
}