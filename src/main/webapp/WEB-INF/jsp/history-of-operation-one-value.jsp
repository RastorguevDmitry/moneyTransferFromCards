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


Welcome ${currentUser.lastName}!!
<div class="container">


    ${value}


</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>