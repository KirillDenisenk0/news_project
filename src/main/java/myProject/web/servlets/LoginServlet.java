package myProject.web.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import myProject.web.model.User;
import myProject.web.service.UserService;
import myProject.web.util.JspHelp;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelp.getPath("login")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Optional<User> maybeUser = userService.login(req.getParameter("Email"), req.getParameter("Password"));
            maybeUser.ifPresentOrElse(user -> onLoginSuccess(user,req,resp),
                    () -> onLoginFailed(req,resp));

        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @SneakyThrows
    private void onLoginFailed(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(401);
        resp.sendRedirect("http://localhost:8080/myProjectNewss/login");
    }

    @SneakyThrows
    private void onLoginSuccess(User user, HttpServletRequest req, HttpServletResponse resp)  {
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("http://localhost:8080/myProjectNewss/news");
    }
}
