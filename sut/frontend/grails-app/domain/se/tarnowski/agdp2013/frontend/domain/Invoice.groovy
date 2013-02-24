package se.tarnowski.agdp2013.frontend.domain

import se.tarnowski.agdp2013.frontend.PaymentStatus

class Invoice {
    Long id
    BigInteger invoiceNumber
    Date invoiceDate
    Date dueDate
    Date paymentDate
    BigDecimal amount
    Customer customer
    PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    static belongsTo = Customer

    static constraints = {
        invoiceNumber(nullable: false)
        invoiceDate(nullable: false)
        dueDate(nullable: true)
        paymentDate(nullable: true)
        amount(nullable: false)
        customer(nullable: false)
        paymentStatus(nullable: false)
    }

    Invoice(BigDecimal invoiceNumber, BigDecimal amount) {
        this.invoiceNumber = invoiceNumber
        this.amount = amount
        invoiceDate = new Date()
    }
}
