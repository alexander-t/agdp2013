package se.tarnowski.agdp2013.junit;

import org.junit.Test;
import se.tarnowski.agdp2013.backend.DatabaseResetter;
import se.tarnowski.agdp2013.backend.SimpleOsCommandExecutor;
import se.tarnowski.agdp2013.web.CustomerPage;
import se.tarnowski.agdp2013.web.MainPage;
import se.tarnowski.agdp2013.web.WebTestPlatform;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class InvoicePaymentHappyPathTest {

    final String baseDir = "d:\\src\\agdp2013";
    final String chromeDriverDir = baseDir + "\\tests\\chromedriver.exe";
    final String sutDir = baseDir + "\\sut";

    final String jdbcUrl = "jdbc:mysql://192.168.0.70:3306/agdp2013";
    final String frontendUrl = "http://localhost:8080/frontend";

    @Test
    public void test() {

        new DatabaseResetter(jdbcUrl, "agdp", "2013").reset();

        if (new SimpleOsCommandExecutor(sutDir,
                Arrays.asList("invoice_payments.bat", "invoice_payments.txt")).run() != 0) {
            fail("Failed to run import");
        }

        WebTestPlatform webTestPlatform = new WebTestPlatform(chromeDriverDir, frontendUrl);
        try {
            MainPage mainPage = webTestPlatform.startOnMainPage();
            CustomerPage customerPage = mainPage.searchForCustomer("Sven");
            customerPage.clickInvoicesLink();
            System.err.println(customerPage.getPaymentStatusOOfFirstInvoice());
        } finally {
            webTestPlatform.shutdown();
        }
    }
}
