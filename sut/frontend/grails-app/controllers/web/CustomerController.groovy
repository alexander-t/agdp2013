package web

import se.tarnowski.agdp2013.frontend.domain.Customer
import se.tarnowski.agdp2013.frontend.domain.Invoice

class CustomerController {

    def index() {
        if (params.q == null) {
            render(view: "/customer/no_customer")
            return
        }

        Customer customer = Customer.findByFirstName(params.q)
        if (customer == null) {
            customer = Customer.findByLastName(params.q)
            if (customer == null) {
                render(view: "/customer/no_customer")
            } else {
                return [customer: customer]
            }
        } else {
            return [customer: customer]
        }
    }

    def account() {
        Customer customer = Customer.get(params.id)
        if (customer == null) {
            render(view: "no_customer")
        } else {
            render(view: "account", model: [customer: customer])
        }
    }

    def invoice() {
        Customer customer = Customer.get(params.id)
        if (customer == null) {
            render(view: "no_customer")
        } else {
            render(view: "invoices", model: [customer: customer, invoices: Invoice.findByCustomer(customer)])
        }
    }
}
