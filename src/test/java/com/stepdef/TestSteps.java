package com.stepdef;

import day4.pom.LoginPage;
import day4.utils.DriverUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestSteps {
	LoginPage lp = new LoginPage();
	
	@Given("enter the url")
	public void enter_the_url() {
		DriverUtils.getDriver().get("https://demowebshop.tricentis.com/");
	}

	@When("enter valid username {string}  and password {string}")
	public void enter_valid_username_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("when");
	}

	@Then("click on Login button")
	public void click_on_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("then");
	}
}
