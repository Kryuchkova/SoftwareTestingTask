package autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage extends WebDriverAutoSettings
{
    private WebDriverWait waiter = new WebDriverWait(driver, 10);
    @Step("Click on the sign in button")
    public AccountOptions clickBtnSignIn()
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        //вхождение в аккаунт
        waiter.until(ExpectedConditions.visibilityOf(myProfile));
        myProfile.click();
        return new AccountOptions();
    }

    @Step("Check the change of entering title")
    public void textBtnCheckUp()
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        //проверка соответствия названия заголовка
        waiter.until(ExpectedConditions.visibilityOf(myProfile));
        Assert.assertTrue(myProfile.getText().equals("Мой профиль"));
    }

    @Step("Check the login in header")
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
        driver.findElement(By.cssSelector("span.region-form-opener > span > span")).click();
        //вписывание другого города
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys(city);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
    }

    public void clickBtnCity()
    {
        //нажатие на кнопку "продолжить с новым регионом"
        driver.findElement(By.cssSelector("div.header2-region-popup > button")).click();
        driver.navigate().refresh();
    }

    public void cityTxtCheckUp(String city)
    {
        Assert.assertTrue(driver.findElement(By.cssSelector("span.link__inner")).getText().equals(city));
    }

    public PersonalAccountPage changePageToPerArea()
    {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Настройки
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Настройки']")));
        return new PersonalAccountPage();
    }

    public CatalogPage goToCatalog(String request)
    {
        //переходим в раздел электрических зубных щеток
        driver.findElement(By.xpath("//input[@name='text']")).sendKeys(request);
        //нажатие на кнопку "Найти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return new CatalogPage();
    }
}