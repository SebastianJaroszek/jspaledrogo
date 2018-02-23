<%@ page import="pl.dominisz.Cart" %>
<%@ page import="pl.dominisz.CartItem" %>
<%@ page import="pl.dominisz.Product" %>
<%@ page import="pl.dominisz.ProductRepository" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ProductRepository productRepository = ProductRepository.getInstance();
%>
<html>
<head>
    <title>Złóż zamówienie</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getCartItems().isEmpty()) {
            for (CartItem cartItem : cart.getCartItems()) {
                Product product = productRepository.findById(cartItem.getId());
                if (product != null) {
                    productRepository.setCount(product.getId(),
                            product.getCount() - cartItem.getQuantity());
                }
            }
            cart.getCartItems().clear();
            out.print("<h1>Udało się złożyć zamówienie</h1>");
        } else {
            out.print("<h1>Koszyk jest pusty</h1>");
        }
    %>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</div>
</body>
</html>
