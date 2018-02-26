<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona logowania</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h2>Formularz logowania</h2>
    <p>
        ${message}
    </p>
    <form action="login" method="post">
        Nazwa użytkownika: <input type="text" name="username">
        Hasło: <input type="password" name="password">
        <input type="hidden" name="destination" value="${destination}">
        <input type="submit" value="zaloguj">
    </form>

    <a class="btn waves-effect waves-light" href="homepage.jsp">WRÓĆ NA STRONĘ GŁÓWNĄ</a>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>