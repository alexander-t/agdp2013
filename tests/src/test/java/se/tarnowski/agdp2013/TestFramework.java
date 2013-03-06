package se.tarnowski.agdp2013;

import se.tarnowski.agdp2013.backend.DatabaseRoutines;
import se.tarnowski.agdp2013.backend.InvoicePaymentFileWriter;
import se.tarnowski.agdp2013.backend.SimpleOsCommandExecutor;
import se.tarnowski.agdp2013.web.CustomerPage;
import se.tarnowski.agdp2013.web.MainPage;
import se.tarnowski.agdp2013.web.WebTestPlatform;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TestFramework {
    public static final String baseDir = "c:\\src\\agdp2013";
    public static final String chromeDriverDir = baseDir + "\\tests\\chromedriver.exe";
    public static String sutDir = baseDir + "\\sut";

    public static final String jdbcUrl = "jdbc:mysql://localhost:3306/agdp2013";
    public static final String dbUser = "agdp";
    public static final String dbPassword = "2013";
    public static final String frontendUrl = "http://localhost:8080/frontend";

    public static final SimpleDateFormat CRM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static DatabaseRoutines dbRoutines = new DatabaseRoutines(jdbcUrl, dbUser, dbPassword);

    public static void emptyDatabase() {
        dbRoutines.reset();
    }

    public static void addCustomerToDatabase(String customerFirstName, String invoiceNumber) {
        dbRoutines.createCustomer(customerFirstName, invoiceNumber);
    }

    public static File createInvoicePaymentFile(String customerName, String invoiceNumber) {
        File paymentFile = new File(sutDir, "invoice_payment.txt");
        InvoicePaymentFileWriter testFileWriter = new InvoicePaymentFileWriter(paymentFile);
        testFileWriter.addPayment("20130101", 120, "1234567-8", invoiceNumber, customerName.toUpperCase(), "Storgatan 12", "11223", "Stockholm");
        testFileWriter.close();
        return paymentFile;
    }

    public static void importPaymentFile(File paymentFile) {
        int exitCode = new SimpleOsCommandExecutor(sutDir,
                Arrays.asList("invoice_payments.bat", paymentFile.getName())).run();
        if (exitCode != 0) {
            throw new IllegalStateException("Import failed with exit code " + exitCode);
        }
    }

    public static InvoiceStatus findCustomersInvoiceInCms(String customerName) {
        WebTestPlatform webTestPlatform = new WebTestPlatform(chromeDriverDir, frontendUrl);
        try {
            MainPage mainPage = webTestPlatform.startOnMainPage();
            CustomerPage customerPage = mainPage.searchForCustomer(customerName);
            customerPage.clickInvoicesLink();
            InvoiceStatus invoiceStatus = new InvoiceStatus();
            invoiceStatus.paymentDate = customerPage.getPaymentDateOfFirstInvoice();
            invoiceStatus.paymentStatus = customerPage.getPaymentStatusOfFirstInvoice();
            return invoiceStatus;
        } finally {
            webTestPlatform.shutdown();
        }
    }

    public static String todayAsString() {
        return CRM_DATE_FORMAT.format(new Date());
    }

    public static class InvoiceStatus {
        public String paymentDate;
        public String paymentStatus;
    }
}
