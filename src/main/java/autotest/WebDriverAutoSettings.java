package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class WebDriverAutoSettings
{
    private static ChromeDriver chDriver;
    public static EventFiringWebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp()
    {
        ScreenShotMaker shot = new ScreenShotMaker();
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        chDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(chDriver);
        driver.manage().window().maximize();
        // открытие сайта
        driver.get("https://beru.ru/");
        driver.register(shot);
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