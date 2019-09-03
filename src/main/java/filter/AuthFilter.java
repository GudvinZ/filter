package filter;


import model.Role;
import model.User;
import service.RoleService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class AuthFilter implements Filter {
    private FilterConfig config;

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
            RoleService roles = RoleService.getInstance();

            switch (path) {
                case "admin":
                    if (user.getRoles().contains(roles.getUniqueByParam("admin", "role"))) {
                        filterChain.doFilter(req, resp);
                        return;
                    }
                    break;
                case "user":
                    if (user.getRoles().contains(roles.getUniqueByParam("user", "role")) || user.getRoles().contains(roles.getUniqueByParam("admin", "role"))) {
                        filterChain.doFilter(req, resp);
                        return;
                    }
                    break;
                default:
                    resp.sendError(500);
                    return;
            }
            resp.sendRedirect("/noAccess");
        } else {
            resp.sendRedirect("/");
        }
    }
}
