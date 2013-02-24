package se.tarnowski.agdp2013

import se.tarnowski.agdp2013.customer.HibernateCustomerRepository
import se.tarnowski.agdp2013.customer.Customer
import se.tarnowski.agdp2013.invoice.Invoice
import se.tarnowski.agdp2013.invoice.HibernateInvoiceRepository

def sessionFactory = HibernateConfiguration.buildSessionFactory()
def customerRepository = new HibernateCustomerRepository(sessionFactory)
def invoiceRepository = new HibernateInvoiceRepository(sessionFactory)


def c = new Customer(firstName: "Adam", lastName: "Andersson", streetAddress: "Adolfsgatan 10", cityAddress: "110 99 Stockholm")
customerRepository.addCustomer(c)
invoiceRepository.addInvoice(new Invoice(11223344, 120, c))

c = new Customer(firstName: "Sven", lastName: "Svensson", streetAddress: "Stockholmsvägen 100", cityAddress: "111 11 Stockholm")
customerRepository.addCustomer(c)
invoiceRepository.addInvoice(new Invoice(12345678, 120, c))
