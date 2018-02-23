package pl.dominisz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/showAllProducts", "/showProductDetails"})
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

    private void showProductDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pokaz_szczegoly_produktu.jsp");
        //TODO znaleźć produkt i wstawić do request
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
