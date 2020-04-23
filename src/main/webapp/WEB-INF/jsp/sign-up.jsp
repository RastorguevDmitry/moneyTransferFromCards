<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Transfer</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>



<form:form method="post" modelAttribute="currentUser">
    <fieldset class="form-group" >
        <form:label path="firstName">firstName</form:label>
        <form:input path="firstName" type="text"
                    class="form-control" required="required"/>
        <form:errors path="firstName" cssClass="text-warning"/>
    </fieldset>

    <fieldset class="form-group">
        <form:label path="lastName">lastName</form:label>
        <form:input path="lastName" type="text"
                    class="form-control" required="required"/>
        <form:errors path="lastName" cssClass="text-warning"/>
    </fieldset>

    <fieldset class="form-group">
        <form:label path="middleName">middleName</form:label>
        <form:input path="middleName" type="text"
                    class="form-control" required="required"/>
        <form:errors path="middleName" cssClass="text-warning"/>
    </fieldset>

    <form:form method="post" modelAttribute="userPrivateData">
    <fieldset class="form-group">
        <form:label path="login">login</form:label>
        <form:input path="login" type="text"
                    class="form-control" required="required"/>
        <form:errors path="login" cssClass="text-warning"/>
        <font color="red">${errorMessage}</font>
    </fieldset>

    <fieldset class="form-group" >
        <form:label path="password">Password</form:label>
        <form:input path="password" type="password"
                    class="form-control" required="required"

        />
        <form:errors path="password" cssClass="text-warning"/>
    </fieldset>


    <button type="submit" class="btn btn-success">Continue</button>
</form:form>
</form:form>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>