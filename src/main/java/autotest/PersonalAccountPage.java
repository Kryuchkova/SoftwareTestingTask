package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

public class PersonalAccountPage extends WebDriverAutoSettings {
    private WebDriverWait waiter = new WebDriverWait(driver, 30);

    public void changePageDriver(String oldTab, String option)
    {
        driver.findElement(By.linkText(option)).click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        //переходим на дочернюю страницу — точнее в раздел настройки личного кабинета
        driver.switchTo().window(newTab.get(0));
    }

    public void citiesCheckUp()
    {
        //проверка соответствия региона на странице и в личном кабинете
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("region")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span.link__inner")).getText().equals(driver.findElement(By.cssSelector("h2.n-headline__content > span")).getText()));
    }

    public void offPageDriver (String oldTab)
    {
        //закрываем текущюю вкладку
        driver.close();
        //возвращаемся на родительскую страницу
        driver.switchTo().window(oldTab);
    }
}