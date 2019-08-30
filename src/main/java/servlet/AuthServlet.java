package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            req.setAttribute("isEmptyForm", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            boolean isValidate = UserService.getInstance().validateUser(login, password);
            req.setAttribute("isValidate", isValidate);
            if(isValidate) {
                req.getSession().setAttribute("currentUser", UserService.getInstance().getUserByLoginAndPassword(login, password));
                resp.sendRedirect("/admin");
            } else {
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }
}
