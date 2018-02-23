<%@ page import="pl.dominisz.*" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ProductRepository productRepository = ProductRepository.getInstance();
    Cashbox cashbox = new Cashbox();
%>
<html>
<head>
    <title>Pokaż zawartość koszyka</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h2>Lista produktów w koszyku</h2>
    <ul class="collection">
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && !cart.getCartItems().isEmpty()) {
                for (CartItem cartItem : cart.getCartItems()) {
                    Product product = productRepository.findById(cartItem.getId());
        %>
        <li class="collection-item"><%= product.getName() %>, <%= product.getPrice() %>,
            sztuk: <%= cartItem.getQuantity() %>
        </li>
        <%
                }
            }
        %>
        <li class="collection-item">
            Całkowita wartość koszyka: <%= cashbox.getTotalPrice(cart) %>
        </li>
    </ul>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
