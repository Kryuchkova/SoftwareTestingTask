package autotest;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotMaker extends AbstractWebDriverEventListener {

    private String today()
    {
        SimpleDateFormat timeScreen = new SimpleDateFormat("dd.mm.yyyy 'at' hh.mm.ss");
        return timeScreen.format(new Date());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver)
    {
        takeScreenShot(today());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver)
    {
        takeScreenShot(today());
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend)
    {
        takeScreenShot(today());
    }

    @Attachment(value = "{0}", type = "image/png")
    private byte[] takeScreenShot(String date)
    {
        return ((TakesScreenshot) WebDriverAutoSettings.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}