package se.tarnowski.agdp2013.junit;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static se.tarnowski.agdp2013.TestFramework.*;

public class InvoicePaymentHappyPathTest {

    @Before
    public void setUp() {
        resetDatabase();
    }

    @Test
    public void aCorrectInvoicePaymentMarksAnInvoiceAsPaid() {
        final String customerFirstName = "Sven";

        File paymentFile = createInvoicePaymentFile(customerFirstName + " Svensson", "12345678");
        importPaymentFile(paymentFile);

        InvoiceStatus invoiceStatus = findCustomersInvoiceInCms(customerFirstName);
        assertEquals("Payment date today", todayAsString(), invoiceStatus.paymentDate);
        assertEquals("Payment status", "PAID", invoiceStatus.paymentStatus);
    }
}
