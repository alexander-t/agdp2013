package se.tarnowski.agdp2013.customer

import org.hibernate.SessionFactory
import se.tarnowski.agdp2013.TransactionHelper
import se.tarnowski.agdp2013.invoice.Invoice

@Mixin(TransactionHelper)
class HibernateCustomerRepository {
    private SessionFactory sessionFactory

    HibernateCustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
    }

    void addCustomer(Customer customer) {
        withTransaction {session ->
            session.save(customer)
        }
    }
}
