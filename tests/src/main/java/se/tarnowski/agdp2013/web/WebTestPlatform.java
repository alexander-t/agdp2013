package se.tarnowski.agdp2013.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class WebTestPlatform {

    final String url;
    final WebDriver webDriver;

    public WebTestPlatform(String webDriverDir, String url) {
        this.url = url;
        System.setProperty("webdriver.chrome.driver", webDriverDir);
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public MainPage startOnMainPage() {
        webDriver.get(url);
        MainPage mainPage = new MainPage(webDriver);
        PageFactory.initElements(webDriver, mainPage);
        return mainPage;
    }

    public void shutdown() {
        webDriver.quit();
    }
}
