package myProject.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.dto.NewUserDto;
import myProject.exeception.DuplicateException;
import myProject.exeception.ValidationException;
import myProject.web.service.UserService;
import myProject.web.util.JspHelp;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewUserDto newUserDto = NewUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .country(req.getParameter("country"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();
        try{
            userService.save(newUserDto);
            resp.sendRedirect("/login");
        }
        catch (ValidationException e ) {
            req.setAttribute("errors",e.getErrorList());
            req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
        }
        catch (DuplicateException | SQLException e){
            req.setAttribute("duplicate",e);
            req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
        }
    }
}
