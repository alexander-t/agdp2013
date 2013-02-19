package se.tarnowski.agdp2013.invoice

import org.hibernate.cfg.AnnotationConfiguration
import org.hibernate.SessionFactory

class HibernateInvoiceRepository implements InvoiceRepository {

    private SessionFactory sessionFactory;

    HibernateInvoiceRepository() {
        sessionFactory = configureHibernate().buildSessionFactory()
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
        }
    }

    @Override
    Invoice findByInvoiceNumber(long invoiceNumber) {
        withTransaction { session ->
            def query = session.createQuery("from Invoice i where i.invoiceNumber=:invoiceNumber")
            query.setParameter("invoiceNumber", invoiceNumber)
            return query.uniqueResult()
        }
    }

    private configureHibernate() {
        def hibernateProps = [
                "hibernate.dialect": "org.hibernate.dialect.HSQLDialect",
                "hibernate.connection.driver_class": "org.hsqldb.jdbcDriver",
                "hibernate.connection.url": "jdbc:hsqldb:mem:db",
                "hibernate.connection.username": "sa",
                "hibernate.connection.password": "",
                "hibernate.connection.pool_size": "1",
                "hibernate.connection.autocommit": "true",
                "hibernate.cache.provider_class": "org.hibernate.cache.NoCacheProvider",
                "hibernate.hbm2ddl.auto": "create-drop",
                "hibernate.show_sql": "false",
                "hibernate.transaction.factory_class": "org.hibernate.transaction.JDBCTransactionFactory",
                "hibernate.current_session_context_class": "thread"
        ]

        def config = new AnnotationConfiguration()
        hibernateProps.each { k, v -> config.setProperty(k, v) }
        config.addAnnotatedClass(Invoice)
        return config
    }

    def withTransaction(Closure closure) {
        def session = sessionFactory.currentSession
        def tx = session.beginTransaction()
        try {
            return closure.call(session)
        } catch (Throwable t) {
            tx.rollback()
        } finally {
            tx.commit();
        }
    }
}
