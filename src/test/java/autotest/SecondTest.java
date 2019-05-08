package autotest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Base64;

public class SecondTest extends AccountOptions
{
    @DataProvider
    public Object[] citiesData()
    {
        return new Object[] {"Хвалынск", "Энгельс", "Волгоград"};
    }

    @Test (dataProvider = "citiesData")
    public void second_test(String city)
    {
        MainPage mainPage = new MainPage();
        //изменение города
        mainPage.changeCity(city);
        mainPage.clickBtnCity();
        //проверка изменения региона на Хвалынск
        mainPage.cityTxtCheckUp(city);
        //вхождение в аккаунт
        AccountOptions accountOption = mainPage.clickBtnSignIn();
        //вводим логин
        String login = "kryuchkovaaleksandra@yandex.ru";
        accountOption.loginInput(login);
        //вводим пароль
        accountOption.passwordInput("a3J5dWNoMjMwNw==");
        mainPage.changePageToPerArea();
        //переход на дочернюю страницу
        String oldTab = driver.getWindowHandle();
        String option = "Настройки";
        mainPage.changePageDriver(oldTab, option);
        //проверка соответствия региона на старнице и в личном кабинете
        mainPage.citiesCheckUp();
        //возвращаемся на родительскую страницу
        mainPage.offPageDriver(oldTab);
        //выходим из личного кабинета
        accountOption.logOut();
    }
}
