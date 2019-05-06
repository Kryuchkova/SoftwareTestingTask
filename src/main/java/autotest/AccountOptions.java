package autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOptions extends WebDriverAutoSettings {
    public void loginInput(String login){

        //заполнение поля логин
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(login);
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void passwordInput(String password){
        //заполнение поля пароль
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys(password);
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void logOut()
    {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Выход
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Выход']")));
        driver.findElement(By.linkText("Выход")).click();
    }
}
