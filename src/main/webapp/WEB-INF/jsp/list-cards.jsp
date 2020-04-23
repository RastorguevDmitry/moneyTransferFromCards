<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>Card's for ${currentUser.firstName}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>
<div>
    <a type="button" class="btn btn-success"
       href="/">Exit</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-findAllByTimeToCompleteTransferLastTwoHours">history of operation</a>
</div>
<div>

</div>


Welcome ${currentUser.lastName}!! Here you can manage your card
<div class="container">
    <table class="table table-striped">
        <caption>Your cards are</caption>
        <thead>
        <tr>
            <th>Number</th>
            <th>amount of money on the card</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cards}" var="card">
            <tr>
                <td>${card.number}</td>
                <td>${card.amountOfMoneyOnCard}</td>
                <td><a type="button" class="btn btn-success"
                       href="/topup-card-balance?number=${card.number}">top up balance on 500</a></td>
                <td><a type="button" class="btn btn-success"
                       href="/transfer-card?number=${card.number}">transfer money</a></td>
                <td><a type="button" class="btn btn-warning"
                       href="/delete-card?number=${card.number}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a class="button" href="/add-card">Add a Card</a>
    </div>
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>