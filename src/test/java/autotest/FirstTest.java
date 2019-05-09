package autotest;

import org.testng.annotations.Test;

public class FirstTest extends WebDriverAutoSettings
{
    @Test
    public void first_test()
    {
        MainPage mainPage = new MainPage();
        //вхождение в аккаунт
        AccountOptions accountOption = mainPage.clickBtnSignIn();
        //вводим логин
        String login = "kryuchkovaaleksandra@yandex.ru";
        accountOption.loginInput(login);
        //вводим пароль
        accountOption.passwordInput("a3J5dWNoMjMwNw==");
        // проверка соответствия надписи
        mainPage.textBtnCheckUp();
        // проверка соответствия логина
        mainPage.loginTxtCheckUp(login);
    }
}