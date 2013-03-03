package se.tarnowski.agdp2013.fitnesse;

import java.io.File;

import static se.tarnowski.agdp2013.TestFramework.*;

public class InvoicePaymentHappyPath {

    private String customerFirstName;
    private String invoiceNumber;
    private InvoiceStatus invoiceStatus;

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void execute() {
        resetDatabase();
        File paymentFile = createInvoicePaymentFile(customerFirstName + " Svensson", invoiceNumber);
        importPaymentFile(paymentFile);
        invoiceStatus = findCustomersInvoiceInCms(customerFirstName);
    }

    public String paymentStatus() {
        return invoiceStatus.paymentStatus;
    }

    public String paymentDate() {
        return invoiceStatus.paymentDate;
    }
}
