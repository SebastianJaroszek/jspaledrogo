package pl.dominisz;

import sun.security.x509.RDN;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO dodać klasę do sprawdzania użytkownika i hasła
        //najlepiej z bazy danych (najfajniej zaszyfrowane hasło) (PLAIN)
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String destination = req.getParameter("destination");
        if ("sebastian".equals(username) && "sebastian".equals(password)) {
            UserDetails userDetails = new UserDetails(username, LocalDateTime.now());
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userDetails", userDetails);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("placeOrder");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Niepoprawna nazwa użytkownika lub hasło");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
