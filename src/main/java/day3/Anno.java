package day3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
 
public class Anno {
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("before test");
		
	}
	@BeforeClass
	public void beforeclass()
	{
		System.out.println("before class");
	}
	@BeforeMethod
	public void beforemethod()
	{
		System.out.println("before method");
		
	}
	@AfterTest
	public void afterTest()
	{
		System.out.println("after test");
		
	}
	@AfterClass
	public void afterclass()
	{
		System.out.println("after class");
		
	}
	@AfterMethod
	public void Afteremethod()
	{
		System.out.println("After method");
		
	}
 
}