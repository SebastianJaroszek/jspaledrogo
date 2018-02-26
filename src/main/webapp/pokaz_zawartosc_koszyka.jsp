<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pokaż zawartość koszyka</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <c:if test="${not empty products}">
        <h2>Lista produktów w koszyku</h2>
        <ul class="collection">
            <c:forEach items="${products}" var="product">
                <li class="collection-item">${product.name}, ${product.price}zł,
                    sztuk: ${product.quantity}
                </li>
            </c:forEach>
            <li class="collection-item">
                Całkowita wartość koszyka: ${totalPrice}zł
            </li>
        </ul>
    </c:if>
    <c:if test="${empty products}">
        <h2>W koszyku nie ma produktów</h2>
    </c:if>
    <a class="btn waves-effect waves-light" href="homepage.jsp">WRÓĆ NA STRONĘ GŁÓWNĄ</a>
    <a class="btn waves-effect waves-light" href="placeOrder">ZŁÓŻ ZAMÓWIENIE</a>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
