<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta name="layout" content="simple"/>
</head>

<body>
<br/>

<g:render template="customer_details" bean="${customer}"/>

<h4>Account details</h4>
<p><strong>Balance:</strong>&nbsp;${customer.balance}</p>
</body>
</html>