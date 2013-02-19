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

    Invoice findByInvoiceNumber(long invoiceNumber) {
        invoices.find {it.invoiceNumber == invoiceNumber}
    }
}
