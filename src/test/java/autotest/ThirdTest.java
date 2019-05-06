package autotest;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import sun.security.util.Length;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class ThirdTest extends WebDriverAutoSettings{
    @Test
    public void third_test() {
        //изменение города
        //переходим в раздел электрических зубных щеток
        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("электрические зубные щетки");
        //нажатие на кнопку "Найти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='glpricefrom']")));
        //задаем диапазон цен
        driver.findElement(By.xpath("//input[@id='glpricefrom']")).sendKeys("999");
        driver.findElement(By.xpath("//input[@id='glpriceto']")).sendKeys("1999");

        // обновить страницу
        driver.navigate().refresh();

        // раскрываем все страницы до тех пор, пока на странице присутствует кнопка "показать еще"
//        while (driver.findElement(By.xpath("//a[@role='button']")).isDisplayed())
//        {
//            driver.findElement(By.xpath("//a[@role='button']")).click();
//        }

        //составляем массив элементов страниц
        //в данном случае — массив щеток
        List<WebElement> ListPrice = driver.findElements(By.cssSelector("div.search-result-snippet span._1u3j_pk1db span"));
        for (int i = 0; i < ListPrice.size(); i += 2) {
            int val_price = Integer.parseInt(ListPrice.get(i).getText().replaceAll("\\s", ""));
            System.out.println(val_price);
            if (val_price < 999 || val_price > 1999) {
                //throw  new Exception("The items don't conform the conditions!");
            }
        }

        //списко кнопок в корзину
        List<WebElement> ListButton = driver.findElements(By.cssSelector("div.search-result-snippet-cart-button button "));
        System.out.println(ListButton);
        // кладем предпоследнюю щетку в корзину
        String s = "";
        ListButton.get(ListButton.size() - 1).getCssValue(s);
        System.out.println(s);

        //driver.findElement(By.cssSelector()).click();
    }
}

