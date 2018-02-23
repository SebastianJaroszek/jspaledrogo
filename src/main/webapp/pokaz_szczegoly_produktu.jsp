<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.dominisz.ProductRepository" %>
<%@ page import="pl.dominisz.Product" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pokaż szczegóły produktu</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h2>Dane szczegółowe produktu</h2>
    <ul class="collection">
        <li class="collection-item">Nazwa produktu: ${product.name}</li>
        <li class="collection-item">Opis produktu: ${product.description}</li>
        <li class="collection-item">Cena: ${product.price}zł</li>
        <li class="collection-item">Liczba sztuk w magazynie: ${product.count}</li>
        <li class="collection-item">
            <form action="dodaj_do_koszyka.jsp" method="post">
                Liczba sztuk: <input type="number" value="1" name="quantity">
                <input type="submit" value="Dodaj do koszyka">
                <input type="hidden" value="${product.id}" name="id">
            </form>
        </li>
    </ul>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
