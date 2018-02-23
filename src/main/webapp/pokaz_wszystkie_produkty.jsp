<%@ page import="pl.dominisz.Product" %>
<%@ page import="pl.dominisz.ProductRepository" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ProductRepository productRepository = ProductRepository.getInstance();
%>
<html>
<head>
    <title>Pokaż wszystkie produkty</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h2>Lista wszystkich produktów w sklepie</h2>
    <ul class="collection">
        <%
            for (Product product : productRepository.findAll()) {
        %>
        <li class="collection-item"><%= product.getName() %>, cena: <%= product.getPrice() %> zł,
            <a href="pokaz_szczegoly_produktu.jsp?id=<%= product.getId() %>">Pokaż szczegóły produktu</a>
        </li>
        <%
            }
        %>
    </ul>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
