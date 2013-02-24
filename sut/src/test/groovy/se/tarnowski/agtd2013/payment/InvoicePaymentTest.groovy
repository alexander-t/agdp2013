package se.tarnowski.agtd2013.payment

import se.tarnowski.agdp2013.invoice.Invoice
import se.tarnowski.agdp2013.payment.InvoicePayment
import se.tarnowski.agdp2013.payment.PaymentStatus
import se.tarnowski.agtd2013.invoice.ListInvoiceRepository
import se.tarnowski.agdp2013.PaymentEngine

public class InvoicePaymentTest extends GroovyTestCase {

    def final PRICE = 100
    def final PRICE_WITH_FEE = PRICE + 10

    def invoiceRepository;

    @Override
    void setUp() {
        invoiceRepository = new ListInvoiceRepository()
    }

    void testANormalInvoicePaymentOf110SekIsApproved()  {

        def invoice = createInvoice();
        invoiceRepository.addInvoice(invoice)

        PaymentEngine paymentEngine = new PaymentEngine(invoiceRepository)
        paymentEngine.processPayment(createInvoicePayment(PRICE_WITH_FEE))

        assert invoice.paymentStatus == PaymentStatus.PAID
        assert invoice.paymentDate.clearTime().equals(new Date().clearTime())
    }

    void testAnInvoiceWithoutTheInvoicingFeeBeingPaidIsAlsoApproved()  {

        def invoice = createInvoice();
        invoiceRepository.addInvoice(invoice)

        PaymentEngine paymentEngine = new PaymentEngine(invoiceRepository)
        paymentEngine.processPayment(createInvoicePayment(PRICE))

        assert invoice.paymentStatus == PaymentStatus.PAID
        assert invoice.paymentDate.clearTime().equals(new Date().clearTime())
    }

    void testInvoicesWithIncompletePaymentsAreLoggedAsIncomplete() {

        def invoice = createInvoice();
        invoiceRepository.addInvoice(invoice)

        PaymentEngine paymentEngine = new PaymentEngine(invoiceRepository)
        paymentEngine.processPayment(createInvoicePayment(PRICE - 1))

        assert invoice.paymentStatus == PaymentStatus.PARTIALLY_PAID
        assertNull invoice.paymentDate
    }


    def createInvoicePayment(BigDecimal amount) {
        InvoicePayment invoicePayment = new InvoicePayment()
        invoicePayment.amount = amount
        invoicePayment.paymentDate = new Date()
        invoicePayment.invoiceNumber = 123456789
        return invoicePayment
    }

    def createInvoice() {
        Invoice invoice = new Invoice(123456789, 110)
        return invoice
    }
}
