package se.tarnowski.agdp2013.backend

import groovy.sql.Sql

class DatabaseRoutines {

    private Sql sql;

    DatabaseRoutines(jdbcUrl, user, password) {
        sql = Sql.newInstance(jdbcUrl, user, password, "com.mysql.jdbc.Driver")
    }

    def reset() {
        sql.execute("delete from invoice")
        sql.execute("delete from customer")
    }

    def createCustomer(firstName, invoiceNumber) {
        def keys = sql.executeInsert("insert into customer (first_name, last_name, street_address, city_address, balance)" +
                "values (?, 'Svensson', 'Stockholmsvägen 100', '110 11 Stockholm', ?)",
                firstName, 0)
        sql.executeInsert("insert into invoice (amount, invoice_number, invoice_date, due_date, payment_status, customer_id) " +
                "values (?,?,now(),adddate(now(), 30),?,?)", 120, invoiceNumber, "UNPAID", keys[0][0]);
    }
}
