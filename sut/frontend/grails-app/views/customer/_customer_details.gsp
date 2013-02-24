<h4>Customer Details</h4>

<table class="table table-bordered">
    <tbody>
    <tr><td>First Name</td><td>${customer.firstName}</td></tr>
    <tr><td>Last Name</td> <td>${customer.lastName}</td></tr>
    <tr><td>Street Address</td> <td>${customer.streetAddress}</td></tr>
    <tr><td>Postal code/City</td> <td>${customer.cityAddress}</td></tr>
    </tbody>
</table>

<div class="navbar">
    <div class="navbar-inner">
        <ul class="nav">
            <li><g:link controller="customer" action="account" id="${customer.id}">Account</g:link></li>
            <li class="divider-vertical"></li>
            <li><g:link controller="customer" action="invoice" id="${customer.id}">Invoices</g:link></li>
            <li class="divider-vertical"></li>
        </ul>
    </div>
</div>
