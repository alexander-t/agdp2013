package se.tarnowski.agdp2013.backend

import groovy.sql.Sql

class DatabaseResetter {

    private Sql sql;

    DatabaseResetter(jdbcUrl, user, password) {
        sql = Sql.newInstance(jdbcUrl, user, password, "com.mysql.jdbc.Driver")
    }

    def reset() {
        sql.execute("update invoice set payment_status='UNPAID'")
    }
}
