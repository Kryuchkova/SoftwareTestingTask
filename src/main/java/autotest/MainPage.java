package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

public class MainPage extends WebDriverAutoSettings
{
    public AccountOptions clickBtnSignIn()
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        //вхождение в аккаунт
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(myProfile));
        myProfile.click();
        return new AccountOptions();
    }

    public void textBtnCheckUp()
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        //проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(myProfile));
        Assert.assertTrue(myProfile.getText().equals("Мой профиль"));
    }

    public void loginTxtCheckUp(String login)
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        myProfile.click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.header2-user-menu__email")).getText().equals(login));
    }

    public void changeCity(String city)
    {
        //изменение города
        //нажатие на поле "регион"
        // поменять селекторы на спан > спан > спан
        driver.findElement(By.cssSelector("span.region-form-opener > span > span")).click();
        //вписывание другого города
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys(city);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
    }

    public void clickBtnCity()
    {
        //нажатие на кнопку "продолжить с новым регионом"
        driver.findElement(By.cssSelector("div.header2-region-popup > button")).click();
    }

    public void cityTxtCheckUp(String city)
    {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.link__inner")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span.link__inner")).getText().equals(city));
    }

    public void changePageToPerArea()
    {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Настройки
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Настройки']")));
    }

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
        //проверка соответствия региона на старнице и в личном кабинете
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("region")));
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
