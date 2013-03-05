<!DOCTYPE html>
<html lang="en">
<head>
    <title><g:layoutTitle default="Simplistic CRM&trade;"/></title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap.min.css')}">
</head>

<body>
<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="/frontend/">Simplistic CRM&trade;</a>
        </div>
    </div>

    <div class="row">
        <div class="span3">
            <br/>
            <g:form controller="customer" class="form-inline">
                <fieldset>
                <legend>Search</legend>
                <g:textField name="q" type="text" class="input-small" placeholder="Name"/>
                <g:submitButton name="search" class="btn" value="search"/>
                <fieldset>
            </g:form>
        </div>

        <div class="span9">
            <g:layoutBody/>
        </div>
    </div>
</body>
</html>
