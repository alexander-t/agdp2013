Feature: Invoice payment

An unpaid invoice is marked as paid if an invoice payment is received with the exact same amount
as on the invoice. The invoice's payment date is set to the day the payment was received. </p>

 Scenario: An invoice is paid with the correct amount
    Given that customer Sven has an unpaid invoice with number 12345678.
    When a payment with invoice number 12345678 is processed.
    Then the status of the invoice should be PAID
    And its payment date should be set to today
