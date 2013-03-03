package se.tarnowski.agdp2013.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static se.tarnowski.agdp2013.TestFramework.*;

public class InvoicePaymentHappyPathStepDefinitions {

    private File paymentFile;
    private String customerFirstName;
    private InvoiceStatus invoiceStatus;

    @Before
    public void beforeScenario() {
        resetDatabase();
    }

    @Given("that customer (\\w+) has an unpaid invoice with number (\\w+).")
    public void createFile(String customerFirstName, String invoiceNumber) {
        this.customerFirstName = customerFirstName;
        paymentFile = createInvoicePaymentFile(customerFirstName + " Svensson", invoiceNumber);
    }

    @When("a payment with invoice number (\\w+) is processed.")
    public void processPayment(String invoiceNumber ) {
        importPaymentFile(paymentFile);
        invoiceStatus = findCustomersInvoiceInCms(customerFirstName);
    }

    @Then("the status of the invoice should be (\\w+)")
    public void checkPaymentStatus(String expectedPaymentStatus) {
        assertEquals(expectedPaymentStatus, invoiceStatus.paymentStatus);
    }

    @And("its payment date should be set to today")
    public void checkPaymentDate() {
        assertEquals(todayAsString(), invoiceStatus.paymentDate);
    }
}
