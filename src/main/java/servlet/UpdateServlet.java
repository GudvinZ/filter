package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", Long.parseLong(req.getParameter("id")));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        User user = UserService.getInstance().getUserByParam(id, "id");

        if (UserService.getInstance().getUserByParam(login, "login") != null && !login.equals(user.getLogin())) {
            req.setAttribute("isAlreadyExist", true);
            req.setAttribute("id", id);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(req, resp);
            return;
        } else {
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);

            UserService.getInstance().updateUser(user);
        }

        req.getSession().setAttribute("users", UserService.getInstance().getAllUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }
}
