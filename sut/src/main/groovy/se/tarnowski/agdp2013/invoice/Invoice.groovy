package se.tarnowski.agdp2013.invoice

import se.tarnowski.agdp2013.customer.Customer
import se.tarnowski.agdp2013.payment.PaymentStatus

import javax.persistence.*

@Entity(name = "invoice")
class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    BigInteger invoiceNumber
    Date invoiceDate
    Date dueDate
    Date paymentDate
    BigDecimal amount
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    Customer customer;

    Invoice() {}

    Invoice(BigInteger invoiceNumber, BigDecimal amount, Customer customer) {
        this.invoiceNumber = invoiceNumber
        this.amount = amount
        this.customer = customer
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
