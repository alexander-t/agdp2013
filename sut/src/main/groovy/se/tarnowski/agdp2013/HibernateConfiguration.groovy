package se.tarnowski.agdp2013;

import org.hibernate.cfg.AnnotationConfiguration;
import se.tarnowski.agdp2013.invoice.Invoice
import org.hibernate.SessionFactory
import se.tarnowski.agdp2013.customer.Customer
import org.hibernate.cfg.ImprovedNamingStrategy;

class HibernateConfiguration {
    static SessionFactory buildSessionFactory() {
        def hibernateProps = [
                "hibernate.dialect": "org.hibernate.dialect.MySQL5InnoDBDialect",
                "hibernate.connection.driver_class": "com.mysql.jdbc.Driver",
                "hibernate.connection.url": "jdbc:mysql://192.168.0.70:3306/agdp2013",
                "hibernate.connection.username": "agdp",
                "hibernate.connection.password": "2013",
                "hibernate.connection.pool_size": "1",
                "hibernate.connection.autocommit": "true",
                "hibernate.cache.provider_class": "org.hibernate.cache.NoCacheProvider",
                "hibernate.hbm2ddl.auto": "validate",
                "hibernate.show_sql": "true",
                "hibernate.transaction.factory_class": "org.hibernate.transaction.JDBCTransactionFactory",
                "hibernate.current_session_context_class": "thread"
        ]

        def config = new AnnotationConfiguration()
        config.setNamingStrategy(new ImprovedNamingStrategy())
        hibernateProps.each { k, v -> config.setProperty(k, v) }

        config.addAnnotatedClass(Invoice)
        config.addAnnotatedClass(Customer)

        return config.buildSessionFactory()
    }
}
