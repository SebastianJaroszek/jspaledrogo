<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Główna strona sklepu</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h2>Aledrogo</h2>
    <ul class="collection">
        <li class="collection-item"><a href="showAllProducts">Pokaż wszystkie produkty w sklepie</a></li>
        <c:if test="${not empty cart and not empty cart.cartItems}">
            <li class="collection-item"><a href="showCart">Pokaż zawartość koszyka</a></li>
            <li class="collection-item"><a href="placeOrder">Złóż zamówienie</a></li>
        </c:if>
        <li class="collection-item"><a href="showCart">Pokaż zawartość koszyka</a></li>
        <li class="collection-item"><a href="placeOrder">Złóż zamówienie</a></li>
    </ul>


    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
