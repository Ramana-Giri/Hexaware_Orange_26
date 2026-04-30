package day4.com.amazon.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtils {
	static WebDriver driver;

	public static void createDriver() {
		WebDriverManager.chromedriver().setup();
		System.out.println("Driver initialized");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			createDriver();
		}
		return driver;
	}
}