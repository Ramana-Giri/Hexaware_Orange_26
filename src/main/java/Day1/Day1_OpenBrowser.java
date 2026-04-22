package Day1;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_OpenBrowser {
    public static void main(String[] args) throws InterruptedException {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
//        driver.get("https://ramana-giri.github.io/personal-website-project/");
//        driver.manage().window().maximize();
//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());
////		System.out.println(driver.getPageSource()); // used to retrieve Html code
//        driver.quit();
        
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().fullscreen();
        Thread.sleep(5000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        
        // Relative xPath (always starts with //)
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        
        // absolute xPath (contains the complete path from the root) (always starts with /) 
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();
        
        
    }
}
