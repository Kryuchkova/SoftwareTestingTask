package autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Base64;

public class AccountOptions extends WebDriverAutoSettings
{
    private WebDriverWait waiter = new WebDriverWait(driver, 10);
    public static String getDecodePassword(String decodePassword)
    {
        return new String(Base64.getDecoder().decode(decodePassword.getBytes()));
    }

    @Step("Login input")
    public void loginInput(String login)
    {
        //заполнение поля логин
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(login);
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Step("Password input")
    public void passwordInput(String password)
    {
        //заполнение поля пароль
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys(getDecodePassword(password));
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Step("Log out")
    public void logOut()
    {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Выход
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Выход']")));
        driver.findElement(By.linkText("Выход")).click();
    }
}
