package ru.org.autotest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class FirstTest extends  WebDriverAutoSettings {
    @Test
    public void userLogin(){
        //закрытие всплывающего баннера
        driver.findElement(By.cssSelector("div.modal__content > div")).click();
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
        //провекра соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        Assert.assertTrue("Кнопка входа профиля не найдена", driver.findElement(By.cssSelector("div.header2-nav__user")).getText().equals("Мой профиль"));
    }
}
