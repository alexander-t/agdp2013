import se.tarnowski.agdp2013.invoice.HibernateInvoiceRepository
import se.tarnowski.agdp2013.invoice.Invoice
import se.tarnowski.agdp2013.payment.InvoicePaymentParser

if (args.length != 1) {
    System.err.println("Payment file expected! (argument missing)")
    System.exit(100)
}

println args[0]
def invoicePaymentFile = new File(args[0])
if (!(invoicePaymentFile.exists() && invoicePaymentFile.isFile())) {
    System.err.println("Invalid se.tarnowski.agdp2013.payment file! (text file expected)")
    System.exit(110)
}

def invoiceRepository = new HibernateInvoiceRepository()

invoiceRepository.addInvoice(new Invoice(1234567, 110))
invoiceRepository.addInvoice(new Invoice(1234568, 110))

def paymentEngine = new PaymentEngine(invoiceRepository)
def invoicePaymentParser = new InvoicePaymentParser();

invoicePaymentFile.eachLine {line ->
    paymentEngine.processPayment(invoicePaymentParser.parseLine(line))
}

println invoiceRepository.findByInvoiceNumber(1234567)
println invoiceRepository.findByInvoiceNumber(1234568)

