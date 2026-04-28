package day4.test;

import org.testng.annotations.Test;

import day4.pom.LoginPage;
import day4.pom.SearchPage;
import day4.utils.DriverUtils;

public class DemoTest {
	@Test
	public void validateLogin() {
		DriverUtils.getDriver().get("https://demowebshop.tricentis.com/login");
		LoginPage tp = new LoginPage();
		tp.verifyEmail("user@gmail.com");
		tp.verifyPassword("test123");
		tp.loginBtnclick();
	}
	
	@Test(dependsOnMethods = "validateLogin")
	public void validateSearch() {
		SearchPage sp = new SearchPage();
		sp.searchItem("computer");
	}
}