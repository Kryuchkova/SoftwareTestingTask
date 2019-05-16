package autotest;

import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({FailedStepListener.class})
public class CheckCart extends WebDriverAutoSettings{

    @Test
    @Description("Test Description: Test checks the operations with goods in the catalog and cart")
    public void checkCart() {
        MainPage mainPage = new MainPage();
        // переходим в раздел "электрические зубные щетки"
        CatalogPage catalogPage = mainPage.goToCatalog("электрические зубные щетки");
        // нижняя граница цены
        String priceFrom = "999";
        // верхняя граница цены
        String priceTo = "1999";
        // устанавливаем нижнюю границу цены
        catalogPage.setFromPrice(priceFrom);
        // устанавливаем верхнюю границу цены
        catalogPage.setToPrice(priceTo);
        // раскрываем все страницы результата поиска
        catalogPage.getAllResultItems();
        // проверяем элементы на сооответствие цены
        catalogPage.itemsCheckUp(priceFrom, priceTo);
        // добавляем пердпоследнюю щетку в корзину
        catalogPage.addItemIntoCart();
        // переходим в корзину
        CartPage cartPage = catalogPage.goToCart();
        // проверяем сумму, оставшуюся до бесплатной доставки
        cartPage.sumToFreeShipping();
        // проверяем правильность подсчета стоимости заказа
        cartPage.costCheckUp();
        // добавляем еще одну щетку в корзину
        cartPage.addItemToCart();
        // проверяем на наличие бесплатной доставки
        cartPage.sumToFreeShipping();
        // проверяем правильность подсчета стоимости заказа
        cartPage.costCheckUp();
    }
}