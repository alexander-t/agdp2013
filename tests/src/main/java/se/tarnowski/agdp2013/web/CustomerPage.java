package se.tarnowski.agdp2013.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPage {

    private WebDriver webDriver;

    //@FindBy(xpath = "//table[@class='table table-bordered']")
    @FindBy(xpath = "//table/tbody")
    private WebElement customerDetailTable;

    @FindBy(linkText = "Invoices")
    private WebElement invoicesLink;

    @FindBy(xpath = "//table[@class='table table-striped']")
    private WebElement invoiceTable;

    public CustomerPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getFirstName() {
        return customerDetailTable.findElement(By.xpath(".//tr[1]/td[2]")).getText();
    }

    public String getLastName() {
        return customerDetailTable.findElement(By.xpath(".//tr[2]/td[2]")).getText();
    }

    public void clickInvoicesLink() {
        invoicesLink.click();
    }

    public String getPaymentDateOfFirstInvoice() {
        return invoiceTable.findElement(By.xpath(".//tr[1]/td[5]")).getText();
    }

    public String getPaymentStatusOfFirstInvoice() {
        return invoiceTable.findElement(By.xpath(".//tr[1]/td[6]")).getText();
    }
}
