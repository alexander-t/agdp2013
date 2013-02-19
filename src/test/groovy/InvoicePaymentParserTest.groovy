import se.tarnowski.agdp2013.payment.InvoicePaymentParser

class InvoicePaymentParserTest extends GroovyTestCase {

    void testParsingOfCorrectLine() {
        def parser = new InvoicePaymentParser()
        def line = "10020130301       110004443322-1                1234567SVEN SVENSSON                  STOCKHOLMSVÄGEN 100           11111STOCKHOLM                "

        def payment = parser.parseLine(line)

        assertEquals(Date.parse("yyyyMMdd", "20130301").clearTime(), payment.paymentDate.clearTime())
        assertEquals(110.00, payment.amount)
        assertEquals("4443322-1", payment.destinationAccount)
        assertEquals(1234567, payment.invoiceNumber)
    }
}


