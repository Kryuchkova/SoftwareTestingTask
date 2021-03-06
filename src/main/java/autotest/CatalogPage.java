package autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CatalogPage extends WebDriverAutoSettings
{
    private WebDriverWait waiter = new WebDriverWait(driver, 30);

    @Step("Set priceFrom on given price = {0}")
    public void  setFromPrice(String price)
    {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='glpricefrom']")));
        // устанавливаем нижнюю границу цены
        driver.findElement(By.xpath("//input[@id='glpricefrom']")).sendKeys(price);
    }

    @Step("Set priceTo on given price = {0}")
    public  void setToPrice(String price)
    {
        // устанавливаем верхнюю границу цены
        driver.findElement(By.xpath("//input[@id='glpriceto']")).sendKeys(price);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1PQIIOelRL")));
    }

    @Step("Get all matching items from page")
    public void getAllResultItems()
    {
        // раскрываем все страницы до тех пор, пока на странице присутствует кнопка "показать еще"
        while (driver.findElement(By.xpath("//a[@role='button']")).isDisplayed())
        {
            driver.findElement(By.xpath("//a[@role='button']")).click();
            waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " + "'В корзину')]")));
        }
    }

    @Step("Check all items to fulfill price condition: priceFrom = {0} - priceTo = {1}")
    public void itemsCheckUp(String priceFrom, String priceTo)
    {
        //составляем массив элементов страниц
        //в данном случае — массив щеток
        List<WebElement> listPrice = driver.findElements(By.cssSelector("div.search-result-snippet span._1u3j_pk1db span"));
        for (int i = 0; i < listPrice.size(); i += 2) {
            int val_price = Integer.parseInt(listPrice.get(i).getText().replaceAll("\\s", ""));
            Assert.assertTrue(val_price > Integer.parseInt(priceFrom));
            Assert.assertTrue(val_price < Integer.parseInt(priceTo));
        }
    }

    @Step("Add a penultimate toothbush into a shopping cart")
    public void addItemIntoCart()
    {
        waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.search-result-snippet-cart-button button")));
        //список кнопок "в корзину"
        List<WebElement> listButton = driver.findElements(By.cssSelector("div.search-result-snippet-cart-button button"));
        // кладем предпоследнюю щетку в корзину
        listButton.get(listButton.size() - 2).click();
    }

    @Step("Go into shopping cart")
    public CartPage goToCart()
    {
        // переходим в корзину
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()= 'Перейти в корзину']")));
        driver.findElement(By.linkText("Перейти в корзину")).click();
        return new CartPage();
    }
}