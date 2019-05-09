package autotest;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class WebDriverAutoSettings
{
    public static ChromeDriver driver;

    @BeforeMethod
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // открытие сайта
        driver.get("https://beru.ru/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close()
    {
        if (driver.findElement(By.cssSelector("div.header2-nav__user")).getText().equals("Мой профиль"))
        {
            AccountOptions accountOption = new AccountOptions();
            accountOption.logOut();
        }
        driver.quit();
    }
}