package filter;


import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class AuthFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("currentUser") != null) {
            User user = (User) session.getAttribute("currentUser");
            String path = req.getServletPath().split("/")[1];

            switch (path) {
                case "admin":
                    if (user.getRole().equals("admin")) {
                        filterChain.doFilter(req, resp);
                        return;
                    }
                    break;
                case "user":
                    if (user.getRole().equals("user") || user.getRole().equals("admin")) {
                        filterChain.doFilter(req, resp);
                        return;
                    }
                    break;
                default:
                    resp.sendError(500);
                    return;
            }
            resp.sendRedirect("/noAccess");
//            if (user.getRole().equals("admin"))
//                filterChain.doFilter(req, resp);
//            else if (user.getRole().equals("user")) {
//                if (req.getRequestURI().equals(req.getContextPath().concat("/user")))
//                    filterChain.doFilter(req, resp);
//                else
//                    resp.sendRedirect("/user");
//            }
//            else
//                resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/");
        }
    }
}
