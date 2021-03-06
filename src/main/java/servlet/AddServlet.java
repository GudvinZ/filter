package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String[] roles = req.getParameterValues("roles");

        if (login.isEmpty() || password.isEmpty() || name.isEmpty() || roles == null) {
            req.setAttribute("isEmptyForm", true);
        } else {
            req.setAttribute("isValidate", UserService.getInstance().addUser(new User(login, password, name, roles)));
        }

        req.getSession().setAttribute("users", UserService.getInstance().getAllUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }
}

