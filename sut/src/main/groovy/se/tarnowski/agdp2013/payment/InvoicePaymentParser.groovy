package se.tarnowski.agdp2013.payment

class InvoicePaymentParser {
    InvoicePayment parseLine(String line) {
        def payment = new InvoicePayment()
        payment.paymentDate = Date.parse("yyyyMMdd", line[3..10])
        payment.amount = new BigDecimal(line[11..20].trim() + "." + line[21..22]) // Ignore locale issues. Just go with the dot.
        payment.destinationAccount = line[23..31]
        payment.invoiceNumber = Long.parseLong(line[32..54].trim())
        payment.payerName = line[55..84]
        payment.street = line[85..114]
        payment.postalCode = line[115..119]
        payment.city = line[120..149]
        return payment
    }
}

