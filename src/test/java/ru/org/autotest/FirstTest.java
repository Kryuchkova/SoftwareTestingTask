package ru.org.autotest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FirstTest extends AccountOptions {
    @Test
    public void first_test() {
        //вхождение в аккаунт
        LogIn();
        //проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.header2-nav__user")).getText().equals("Мой профиль"));
        //выходим из личного кабинета
        LogOut();
    }
}