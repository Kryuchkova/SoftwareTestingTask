package ru.org.autotest;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class WebDriverAutoSettings {
     public ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // открытие сайта
        driver.get("https://beru.ru/");
        //закрытие всплывающего баннера
        driver.findElement(By.cssSelector("div.modal__content > div")).click();
    }

    @AfterMethod
    public void close(){
         driver.quit();
    }
}
