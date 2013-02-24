package se.tarnowski.agdp2013

import se.tarnowski.agdp2013.payment.InvoicePaymentParser
import se.tarnowski.agdp2013.customer.HibernateCustomerRepository
import se.tarnowski.agdp2013.invoice.HibernateInvoiceRepository

if (args.length != 1) {
    System.err.println("Payment file expected! (argument missing)")
    System.exit(100)
}

def invoicePaymentFile = new File(args[0])
if (!(invoicePaymentFile.exists() && invoicePaymentFile.isFile())) {
    System.err.println("Invalid se.tarnowski.agdp2013.payment file! (text file expected)")
    System.exit(110)
}

def sessionFactory = HibernateConfiguration.buildSessionFactory()
def invoiceRepository = new HibernateInvoiceRepository(sessionFactory)
def paymentEngine = new PaymentEngine(invoiceRepository)
def invoicePaymentParser = new InvoicePaymentParser();

invoicePaymentFile.eachLine {line ->
    paymentEngine.processPayment(invoicePaymentParser.parseLine(line))
}
