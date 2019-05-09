package autotest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SecondTest extends WebDriverAutoSettings
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
        //проверка изменения региона на заданный город
        mainPage.cityTxtCheckUp(city);
        //вхождение в аккаунт
        AccountOptions accountOption = mainPage.clickBtnSignIn();
        //вводим логин
        String login = "kryuchkovaaleksandra@yandex.ru";
        accountOption.loginInput(login);
        //вводим пароль
        accountOption.passwordInput("a3J5dWNoMjMwNw==");
        // переходим в "Настройки"
        PersonalAccountPage personalAccountPage = mainPage.changePageToPerArea();
        //переход на дочернюю страницу
        String oldTab = driver.getWindowHandle();
        String option = "Настройки";
        personalAccountPage.changePageDriver(oldTab, option);
        //проверка соответствия региона на старнице и в личном кабинете
        personalAccountPage.citiesCheckUp();
        //возвращаемся на родительскую страницу
        personalAccountPage.offPageDriver(oldTab);
    }
}