<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta name="layout" content="simple"/>
</head>

<body>
<br/>

<g:render template="customer_details" bean="${customer}"/>

<g:if test="${invoices != null}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Invoice Date</th>
            <th>Amount</th>
            <th>Due Date</th>
            <th>Payment Date</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${invoices}" var="i">
            <tr>
                <td><g:formatDate format="yyyy-MM-dd" date="${i.invoiceDate}"/></td>
                <td>${i.amount}</td>
                <td><g:formatDate format="yyyy-MM-dd" date="${i.dueDate}"/></td>
                <td><g:formatDate format="yyyy-MM-dd" date="${i.paymentDate}"/></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
</body>
</html>