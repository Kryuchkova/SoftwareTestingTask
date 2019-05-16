package autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage extends WebDriverAutoSettings
{
    private WebDriverWait waiter = new WebDriverWait(driver, 30);

    @Step("Check free shipping")
    public void sumToFreeShipping()
    {
        // проверка значения “До бесплатной доставки осталось”
        String message = driver.findElement(By.cssSelector("div._3yDgi6ylNe")).getText();
        if (!message.contains("бесплатную доставку"))
        {
            String restSum = driver.findElement(By.cssSelector("div._3yDgi6ylNe > span > span")).getText();
            int index = restSum.indexOf(" ");
            Assert.assertTrue(Integer.parseInt(restSum.substring(0, index)) > 0);
        }
        else
        {
            Assert.assertTrue(driver.findElement(By.cssSelector("b.voCFmXKfcL._1O0RrvwYg5")).getText().equals("бесплатную доставку"));
        }
    }

    @Step("Check the purchase price")
    public void costCheckUp()
    {
        // проверка, что итоговая цена равна <стоимость щетки> + <доставка>
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-auto='total-price']")));
        String price = driver.findElement(By.xpath("//div[@data-auto='total-items']/span[@data-auto='value']")).getText().replaceAll("[\u20BD ]", "");
        String cost = driver.findElement(By.xpath("//div[@data-auto='total-price']/span[@class='_1oBlNqVHPq']")).getText().replaceAll("[\u20BD ]", "");
        String delivery = driver.findElement(By.xpath("//div[@data-auto='total-delivery']/span[@data-auto='value']")).getText();
        if (delivery.equals("бесплатно"))
            Assert.assertTrue(Integer.parseInt(price) == Integer.parseInt(cost));
        else {
            delivery = delivery.replaceAll("[\u20BD ]", "");
            Assert.assertTrue(Integer.parseInt(price) + Integer.parseInt(delivery) == Integer.parseInt(cost));
        }
    }

    @Step("Add the second item into shopping cart")
    public void addItemToCart()
    {
        // добавление еще одной щетки в корзину
        driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
    }
}