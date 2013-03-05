package se.tarnowski.agdp2013.invoice

import org.hibernate.SessionFactory
import se.tarnowski.agdp2013.TransactionHelper

@Mixin(TransactionHelper)
class HibernateInvoiceRepository implements InvoiceRepository {

    private SessionFactory sessionFactory;

    HibernateInvoiceRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
    }

    @Override
    void addInvoice(Invoice invoice) {
        withTransaction {session ->
            session.save(invoice)
        }
    }

    @Override
    void updateInvoice(Invoice invoice) {
        withTransaction { session ->
            session.update(invoice)
            session.update(invoice.customer)
        }
    }

    @Override
    Invoice findByInvoiceNumber(BigInteger invoiceNumber) {
        withTransaction { session ->
            def query = session.createQuery("from invoice i where i.invoiceNumber=:invoiceNumber")
            query.setParameter("invoiceNumber", invoiceNumber)
            return query.uniqueResult()
        }
    }
}
