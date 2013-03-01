package se.tarnowski.agtd2013.payment

import se.tarnowski.agdp2013.payment.InvoicePaymentParser

class InvoicePaymentParserTest extends GroovyTestCase {

    void testParsingOfCorrectLine() {
        def parser = new InvoicePaymentParser()
        def line = "10020130301       110004443322-1                1234567[         PAYER NAME         ][       STREET ADDRESS       ]11111[            CITY            ]"

        def payment = parser.parseLine(line)

        assertEquals(Date.parse("yyyyMMdd", "20130301").clearTime(), payment.paymentDate.clearTime())
        assertEquals(110.00, payment.amount)
        assertEquals("4443322-1", payment.destinationAccount)
        assertEquals(1234567, payment.invoiceNumber)
        assertEquals("[         PAYER NAME         ]", payment.payerName)
        assertEquals("[       STREET ADDRESS       ]", payment.street)
        assertEquals("11111", payment.postalCode)
        assertEquals("[            CITY            ]", payment.city)
    }
}
