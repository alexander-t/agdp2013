package se.tarnowski.agdp2013.payment

import se.tarnowski.agdp2013.payment.InvoicePayment

class InvoicePaymentParser {
    InvoicePayment parseLine(String line) {
        def payment = new InvoicePayment()
        payment.paymentDate = Date.parse("yyyyMMdd", line[3..11])
        payment.amount = new BigDecimal(line[12..20].trim() + "." + line[20..22]) // Ignore locale issues. Just go with the dot.
        payment.destinationAccount = line[23..31]
        payment.invoiceNumber = Long.parseLong(line[32..54].trim())
        return payment
    }
}

