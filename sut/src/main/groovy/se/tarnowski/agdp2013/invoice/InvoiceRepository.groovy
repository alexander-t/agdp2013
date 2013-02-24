package se.tarnowski.agdp2013.invoice

interface InvoiceRepository {

    void addInvoice(Invoice invoice)

    void updateInvoice(Invoice invoice)

    Invoice findByInvoiceNumber(BigInteger invoiceNumber)


}
