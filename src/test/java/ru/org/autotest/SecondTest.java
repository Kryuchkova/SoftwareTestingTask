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

public class SecondTest extends AccountOptions{
    @Test
    public void second_test(){
        //изменение города
        //нажатие на поле "регион"
        driver.findElement(By.cssSelector("div.unique-selling-proposition-line__region > span")).click();
        //вписывание другого города
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("Хвалынск");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
        //нажатие на кнопку "продолжить с новым регионом"
        driver.findElement(By.cssSelector("div.header2-region-popup > button")).click();
        //проверка изменения региона на Хвалынск
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.unique-selling-proposition-line__region")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span.link__inner")).getText().equals("Хвалынск"));

        //вхождение в аккаунт
        LogIn();

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        //наведение курсора мыши на элемент "Мой профиль"
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
        //нажатие на поле Настройки
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Настройки']")));

        //переход на дочернюю страницу
        String oldTab = driver.getWindowHandle();
        String option = "Настройки";
        driver.findElement(By.linkText(option)).click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        //переходим на дочернюю страницу — точнее в раздел настройки личного кабинета
        driver.switchTo().window(newTab.get(0));
        //проверка соответствия региона на старнице и в личном кабинете
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("region")));
        Assert.assertTrue(driver.findElement(By.cssSelector("span.link__inner")).getText().equals(driver.findElement(By.cssSelector("h2.n-headline__content > span")).getText()));

        //закрываем текущюю вкладку
        driver.close();
        //возвращаемся на родительскую страницу
        driver.switchTo().window(oldTab);
        //выходим из личного кабинета
        LogOut();
    }
}
