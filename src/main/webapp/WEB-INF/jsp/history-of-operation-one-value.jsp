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

    <a type="button" class="btn btn-success"
       href="/history-of-operation-findAllByAmountOfMoneyBetween?amountOfMoneyFrom=20&amountOfMoneyTo=2000">findAllByAmountOfMoneyBetween?amountOfMoneyFrom=20&amountOfMoneyTo=2000</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-findAllByIncomingCardNumberIs?incomingCardNumber=9">findAllByIncomingCardNumberIs?incomingCardNumber=9</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-findAllOutgoingOperationByTimeToCompleteTransferLastTwoHours">findAllOutgoingOperationByTimeToCompleteTransferLastTwoHours</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-averageSpendingPerDayForLast30Days">averageSpendingPerDayForLast30Days</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-ratioIncomingToOutgoingTransfersForLast30Days">ratioIncomingToOutgoingTransfersForLast30Days</a>
    <a type="button" class="btn btn-success"
       href="/history-of-operation-operationWithMaximumAmountForLast30Days">operationWithMaximumAmountForLast30Days</a>

</div>
<div>

</div>


Welcome ${currentUser.lastName}!!
<div class="container">


    ${value}


</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>