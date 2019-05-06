package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage extends WebDriverAutoSettings{
    public AccountOptions clickBtnSignIn()
    {
        // кнопка мой профиль
        WebElement myProfile = driver.findElement(By.cssSelector("div.header2-nav__user"));
        //вхождение в аккаунт
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(myProfile));
        myProfile.click();
        return new AccountOptions();
    }

    public void textBtnCheckUp(){
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
}
