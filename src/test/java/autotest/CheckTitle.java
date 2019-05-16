package autotest;

import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({FailedStepListener.class})
public class CheckTitle extends WebDriverAutoSettings
{
    @Test
    @Description("Test Description: Test checks the title of the Enterence and Personal Account")
    public void checkTitle()
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