package se.tarnowski.agdp2013.concordion;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import se.tarnowski.agdp2013.TestFramework;

import java.io.File;

import static se.tarnowski.agdp2013.TestFramework.*;

@RunWith(ConcordionRunner.class)
public class InvoicePaymentHappyPathFixture {

    public Result processPayment(String customerFirstName, String invoiceNumber) {
        emptyDatabase();
        addCustomerToDatabase("Sven", "12345678");

        File paymentFile = createInvoicePaymentFile(customerFirstName + " Svensson", invoiceNumber);
        importPaymentFile(paymentFile);

        TestFramework.InvoiceStatus invoiceStatus = findCustomersInvoiceInCms(customerFirstName);

        Result result = new Result();
        result.paymentStatus = invoiceStatus.paymentStatus;
        result.isPaymentDateToday = todayAsString().equals(invoiceStatus.paymentDate);
        return result;
    }

    public static class Result {
        public String paymentStatus;
        public boolean isPaymentDateToday;
    }
}
