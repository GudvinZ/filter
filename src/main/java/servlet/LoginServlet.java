package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            req.setAttribute("isEmptyForm", true);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        } else {
            boolean isValidate = UserService.getInstance().validate(login, password);
            req.setAttribute("isValidate", isValidate);
            if (isValidate) {
                User user = UserService.getInstance().getUniqueByParam(login, "login");
                req.getSession().setAttribute("currentUser", user);
                resp.sendRedirect("/redirect");
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
            }
        }
    }
}
