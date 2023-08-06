package myProject.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.dto.CommentsDto;
import myProject.exeception.BanException;
import myProject.exeception.CensorshipException;
import myProject.web.model.User;
import myProject.web.service.CommentService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createComments")
public class CreateCommentsServlet extends HttpServlet {
    private static final CreateCommentsServlet INSTANCE = new CreateCommentsServlet();
    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String comment = req.getParameter("comment");
        CommentsDto commentsDto = CommentsDto.builder()
                .text(comment)
                .newsId(req.getParameter("newsId"))
                .authorId(user.getId())
                .build();
        try {
            commentService.create(commentsDto);
        }
        catch (BanException | CensorshipException | SQLException e){
            req.setAttribute("error",e);
        }
        req.setAttribute("newsId",commentsDto.getNewsId());
        req.getRequestDispatcher("/newsText/*").forward(req,resp);
    }

    public static CreateCommentsServlet getInstance(){
        return INSTANCE;
    }
}
