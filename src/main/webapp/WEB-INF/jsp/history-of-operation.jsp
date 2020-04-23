<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>${title}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>
<div>
    <a type="button" class="btn btn-success"
       href="/">Exit</a>
    <a type="button" class="btn btn-success"
       href="/list-cards">Back</a>
</div>
<div>

</div>


Welcome ${currentUser.lastName}!! Here you can manage your card
<div class="container">
    <table class="table table-striped">
        <caption>${title}</caption>
        <thead>
        <tr>
            <th>outgoingCardNumber</th>
            <th>incomingCardNumber</th>
            <th>amountOfMoney</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${transfers}" var="transfer">
            <tr>
                <td>${transfer.outgoingCardNumber}</td>
                <td>${transfer.incomingCardNumber}</td>
                <td>${transfer.amountOfMoney}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>