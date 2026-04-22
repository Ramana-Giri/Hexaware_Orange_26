package Day1;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Day1_RelativeLocator {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();

        //starts-with
        WebElement pimTab = wait.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//span[starts-with(normalize-space(), 'PI')]")
        	));
        System.out.println("Tab Found: " + pimTab.getText());
        
        //ends-with
        String xpath = "//h6[substring(normalize-space(), string-length(normalize-space()) - string-length('board') + 1) = 'board']";
        boolean result = driver.findElements(By.xpath(xpath)).size() > 0;
        System.out.println("Ends-with : " + result);
        
        //torightof
        WebElement profileImg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-userdropdown-img")));
        WebElement userName = driver.findElement(with(By.tagName("p")).toRightOf(profileImg));
        System.out.println("User Name to the right: " + userName.getText());

        //toleftof
        WebElement adminText = driver.findElement(By.xpath("//span[text()='Admin']"));
        WebElement searchIcon = driver.findElement(with(By.tagName("i")).toLeftOf(adminText));
        System.out.println("Located element to the left of Admin tab.");
        
        //near
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        boolean isHelpIconNear = driver.findElements(with(By.tagName("button")).near(dashboardHeader)).size() > 0;
        System.out.println("Is Help button near header? " + isHelpIconNear);
        
        Thread.sleep(1000000);
        driver.quit();
    }
}