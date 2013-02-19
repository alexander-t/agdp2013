package se.tarnowski.agdp2013.invoice

import se.tarnowski.agdp2013.payment.PaymentStatus

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    Date invoiceDate
    Date dueDate
    Date paymentDate
    BigDecimal amount
    long invoiceNumber
    PaymentStatus paymentStatus;

    Invoice() {}

    Invoice(long invoiceNumber, BigDecimal amount) {
        this.invoiceNumber = invoiceNumber
        this.amount = amount
        this.invoiceDate = new Date()
        this.paymentStatus = PaymentStatus.UNPAID
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", invoiceNumber=" + invoiceNumber +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
