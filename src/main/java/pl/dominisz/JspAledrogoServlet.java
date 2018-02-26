package pl.dominisz;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/showAllProducts", "/showProductDetails", "/addToCart", "/showCart", "/placeOrder"})
public class JspAledrogoServlet extends HttpServlet {

    private ProductRepository productRepository = ProductRepository.getInstance();
    private Cashbox cashbox = new Cashbox();

   /* @Resource(name="jdbc/jsp_schema")
    private DataSource dataSource;*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        if (requestUri.endsWith("/addToCart")) {
            addToCart(req, resp);
        } else if (requestUri.endsWith("/placeOrder")) {
            placeOrder(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        if (requestUri.endsWith("/showAllProducts")) {
            showAllProducts(req, resp);
        } else if (requestUri.endsWith("/showProductDetails")) {
            showProductDetails(req, resp);
        } else if (requestUri.endsWith("/showCart")) {
            showCart(req, resp);
        } else if (requestUri.endsWith("/placeOrder")) {
            placeOrder(req, resp);
        }
    }

    private void placeOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("zloz_zamowienie.jsp");
        HttpSession session = req.getSession();
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
            req.setAttribute("message", "zamówienie zostało złożone pomyślnie");
        } else {
            req.setAttribute("message", "koszyk jest pusty");
        }
        requestDispatcher.forward(req, resp);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pokaz_zawartosc_koszyka.jsp");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getCartItems().isEmpty()) {
            List<CartItem> cartItems = cart.getCartItems();
            List<ProductInCart> productInCarts = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                Product product = productRepository.findById(cartItem.getId());
                ProductInCart productInCart = new ProductInCart(product.getName(), product.getPrice(), cartItem.getQuantity());
                productInCarts.add(productInCart);
            }
            req.setAttribute("products", productInCarts);
            req.setAttribute("totalPrice", cashbox.getTotalPrice(cart));
        }

        requestDispatcher.forward(req, resp);
    }



    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("dodaj_do_koszyka.jsp");
        Integer id = Integer.parseInt(req.getParameter("id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        HttpSession session = req.getSession();
        CartItem cartItem = new CartItem(id, quantity);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.add(cartItem);
        /*if (id == null){
            req.setAttribute("message", "Produkt o podanym id nie istnieje");
        } else */
        if (quantity > productRepository.findById(id).getCount()) {
            req.setAttribute("message", "nie ma wystarczającej ilości tego produktu na magazynie");
        } else if (quantity < 1) {
            req.setAttribute("message", "nie możesz kupić mniej niż 1 produkt");
        } else {
            req.setAttribute("message", "Udało się dodać produkt do koszyka :-)");
        }
        /*req.setAttribute("message", "nie udało się dodać do koszyka");*/
        requestDispatcher.forward(req, resp);
    }

    private void showProductDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pokaz_szczegoly_produktu.jsp");
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("product", productRepository.findById(id));
        requestDispatcher.forward(req, resp);
    }

    private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pokaz_wszystkie_produkty.jsp");
        req.setAttribute("products", productRepository.findAll());
        requestDispatcher.forward(req, resp);
    }
}
