package com.automation.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\RG\\eclipse-workspace\\Hexaware_Orange_26\\src\\test\\java\\day6\\src\\test\\resources\\feature",
		glue = "com.automation.stepdef",
		plugin = {"pretty", "html:target/cucumber-report.html"},
		monochrome = true
		)
public class Run {

}
