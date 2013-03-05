package se.tarnowski.agdp2013

import groovy.util.logging.Slf4j
import se.tarnowski.agdp2013.invoice.InvoiceRepository
import se.tarnowski.agdp2013.payment.InvoicePayment
import se.tarnowski.agdp2013.payment.Payment
import se.tarnowski.agdp2013.payment.PaymentStatus

/**
 * This class simulates a legacy payment engine with nested logic and ugly constructs like instanceof.
 * Hopefully it contains some bugs.
 */
@Slf4j
class PaymentEngine {

    private static BigDecimal INVOICING_FEE = 10;
    private InvoiceRepository invoiceRepository

    PaymentEngine(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository
    }

    def processPayment(Payment payment) {
        def invoice = invoiceRepository.findByInvoiceNumber(payment.invoiceNumber)

        if (invoice == null) {
            log.error("Couldn't find invoice with number ${payment.invoiceNumber}");
            return
        }

        if (payment instanceof InvoicePayment) {
            if (payment.amount < invoice.amount - INVOICING_FEE) {
                invoice.paymentStatus = PaymentStatus.PARTIALLY_PAID
            } else {
                invoice.paymentStatus = PaymentStatus.PAID
                invoice.paymentDate = new Date()
                def excess = payment.amount - invoice.amount
                if (excess > 0) {
                    invoice.customer.balance += excess
                }
            }
        }
        invoiceRepository.updateInvoice(invoice)
    }
}