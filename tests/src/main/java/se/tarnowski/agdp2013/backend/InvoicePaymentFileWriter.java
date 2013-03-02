package se.tarnowski.agdp2013.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InvoicePaymentFileWriter {

    private BufferedWriter writer;

    public InvoicePaymentFileWriter(File file) {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addPayment(String paymentDate, long amount, String giroNumber, String invoiceNumber, String name, String street, String postalCode, String city) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            df.parse(paymentDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        if (!giroNumber.matches("^\\d{7}\\-\\d$")) {
            throw new IllegalArgumentException("Invalid account number");
        }

        if (!postalCode.matches("^\\d{5}$")) {
            throw new IllegalArgumentException("Invalid postal code");
        }

        // Not for "performance", but for verbosity
        StringBuilder sb = new StringBuilder();
        sb.append("100"); // Payment type (constant)
        sb.append(paymentDate);
        sb.append(String.format("%10d00", amount));
        sb.append(giroNumber);
        sb.append(String.format("%23s", invoiceNumber));
        sb.append(String.format("%-30s", name.toUpperCase()));
        sb.append(String.format("%-30s", street.toUpperCase()));
        sb.append(postalCode);
        sb.append(String.format("%-30s", city.toUpperCase()));

        try {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
        }
    }
}

