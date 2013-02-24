package se.tarnowski.agdp2013.frontend.domain

class Customer {

    Long id
    String firstName
    String lastName
    String streetAddress
    String cityAddress
    BigDecimal balance = 0;


    static hasMany = [invoices: Invoice]

    static constraints = {
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        streetAddress(nullable: false, blank: false)
        cityAddress(nullable: false, blank: false)
        balance(nullable:  false)
    }
}
