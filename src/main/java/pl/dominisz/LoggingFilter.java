package pl.dominisz;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        servletContext.log("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestUri = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        servletContext.log("requestUri: " + requestUri);
        servletContext.log("queryString: " + queryString);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        servletContext.log("LoggingFilter dostroyed");
    }
}
