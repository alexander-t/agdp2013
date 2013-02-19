import se.tarnowski.agdp2013.payment.*
import se.tarnowski.agdp2013.invoice.InvoiceRepository

class PaymentEngine {

    private static BigDecimal INVOICING_FEE = 10;
    private InvoiceRepository invoiceRepository

    PaymentEngine(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository
    }

    def processPayment(Payment payment) {
        def invoice = invoiceRepository.findByInvoiceNumber(payment.invoiceNumber)
        if (payment instanceof InvoicePayment) {
            if (payment.amount < invoice.amount - INVOICING_FEE) {
                invoice.paymentStatus = PaymentStatus.PARTIALLY_PAID
            } else {
                invoice.paymentStatus = PaymentStatus.PAID
                invoice.paymentDate = new Date()
            }
        }
        invoiceRepository.updateInvoice(invoice)
    }
}