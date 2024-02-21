import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
       // Locale.setDefault(new Locale("en", "US"));
        driver = new ChromeDriver();
        // driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation");

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void loginSuccess() {
        driver.findElement(By.xpath("//input[contains(@id,'email_create')]")).sendKeys("petrova@yahoo.com");
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String textSucces = driver.findElement(By.xpath("//*[@id=\"noSlide\"]/h1")).getText();
        Assert.assertEquals(textSucces, "CREATE AN ACCOUNT");
        //Assert.assertEquals(textSucces, "РЕЄСТРАЦІЯ");
    }

    @Test
    public void loginUnsuccess() {
        driver.findElement(By.xpath("//input[contains(@id,'email_create')]")).sendKeys("petrovayahoo.com");
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        String textSucces = driver.findElement(By.xpath("//ol/li")).getText();
        Assert.assertEquals(textSucces, "Invalid email address.");

    }
}
