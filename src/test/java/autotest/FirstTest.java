package autotest;

import org.testng.annotations.Test;

public class FirstTest extends WebDriverAutoSettings {
    @Test
    public void first_test() {
        MainPage mainPage = new MainPage();
        //вхождение в аккаунт
        AccountOptions accountOption = mainPage.clickBtnSignIn();
        //вводим логин
        accountOption.loginInput("kryuchkovaaleksandra@yandex.ru");
        //вводим пароль
        accountOption.passwordInput("kryuch2307");
        // проверка соответствия надписи
        mainPage.textBtnCheckUp();
        //выходим из личного кабинета
        accountOption.logOut();
    }
}