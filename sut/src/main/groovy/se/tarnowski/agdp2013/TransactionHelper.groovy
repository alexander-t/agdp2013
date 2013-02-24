package se.tarnowski.agdp2013

class TransactionHelper {
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
