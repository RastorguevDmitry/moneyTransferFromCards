<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<html>

	<head>
		<title>Transfer</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
			  rel="stylesheet">

	</head>

	<body>

	<form:form method="post" modelAttribute="userOutgoingTransfer">
	<form:hidden path="firstName"/>


	<form:form method="post" modelAttribute="card">
		<form:hidden path="ownerId"/>
		<form:hidden path="number"/>
		<form:hidden path="amountOfMoneyOnCard"/>

		<fieldset class="form-group">
			<form:label path="Number">Number of Your card: ${card.number}</form:label>
		</fieldset>

		<fieldset class="form-group">
			<form:label path="amountOfMoneyOnCard">amount of money in your card: ${card.amountOfMoneyOnCard}</form:label>
		</fieldset>




	<form:form method="post" modelAttribute="moneyTransfer">
		<form:hidden path="outgoingCardNumber"/>
		<fieldset class="form-group">
			<form:label path="incomingCardNumber">number of the card to transfer money to</form:label>
			<form:input path="incomingCardNumber" type="text"
						class="form-control" required="required"/>
			<form:errors path="incomingCardNumber" cssClass="text-warning"/>
			<font color="red">${errorMessage}</font>
		</fieldset>


		<button type="submit" class="btn btn-success">Continue</button>
	</form:form>
	</form:form>
	</form:form>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>