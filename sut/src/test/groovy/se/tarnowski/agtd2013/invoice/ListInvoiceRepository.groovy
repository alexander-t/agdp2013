package se.tarnowski.agtd2013.invoice

import se.tarnowski.agdp2013.invoice.Invoice
import se.tarnowski.agdp2013.invoice.InvoiceRepository

class ListInvoiceRepository implements InvoiceRepository {
    private List<Invoice> invoices = []

    void addInvoice(Invoice invoice) {
        invoices << invoice
    }

    @Override
    void updateInvoice(Invoice invoice) {
    }

    Invoice findByInvoiceNumber(BigInteger invoiceNumber) {
        invoices.find {it.invoiceNumber == invoiceNumber}
    }
}
