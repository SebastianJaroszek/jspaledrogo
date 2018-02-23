package pl.dominisz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/showAllProducts", "/showProductDetails", "/addToCart"})
public class JspAledrogoServlet extends HttpServlet {

    ProductRepository productRepository = ProductRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        if (requestUri.endsWith("/showAllProducts")) {
            showAllProducts(req, resp);
        } else if (requestUri.endsWith("/showProductDetails")) {
            showProductDetails(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        if (requestUri.endsWith("/addToCart")) {
            addToCart(req, resp);
        }
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
        if (quantity > productRepository.findById(id).getCount()){
            req.setAttribute("message", "nie ma wystarczającej ilości tego produktu na magazynie");
        } else if(quantity < 1){
            req.setAttribute("message", "nie możesz kupić mniej niż 1 produkt");
        }
        else {
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
