package ru.org.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOptions extends WebDriverAutoSettings {
    void LogIn(){
        //вхождение в аккаунт
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //заполнение поля логин
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("kryuchkovaaleksandra@yandex.ru");
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //заполнение поля пароль
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("kryuch2307");
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    void LogOut()
    {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Выход
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Выход']")));
        driver.findElement(By.linkText("Выход")).click();
    }
}
