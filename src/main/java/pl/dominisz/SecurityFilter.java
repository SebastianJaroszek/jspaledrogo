package pl.dominisz;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/placeOrder")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            //TODO sprawdić czas loginDateTime i jeśli za dużo to zalogować ponownie
            chain.doFilter(request, response); // jezeli zalogowany to idzie dalej
        } else {
            String requestUri = httpServletRequest.getRequestURI();

            request.setAttribute("destination", requestUri); // strona na ktora chcial isc przed zalogowaniem
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
