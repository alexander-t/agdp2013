package se.tarnowski.agdp2013.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "search")
    private WebElement searchButton;


    public CustomerPage searchForCustomer(String firstOrLastName) {
        searchBox.sendKeys(firstOrLastName);
        searchButton.submit();

        CustomerPage cp = new CustomerPage(webDriver);
        PageFactory.initElements(webDriver, cp);
        return cp;
    }
}
