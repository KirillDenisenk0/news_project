package myProject.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.dto.NewsDto;
import myProject.web.service.NewsService;
import myProject.web.util.JspHelp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/news")
public class NewsServlet  extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsDto> allNews = null;
        try {
            allNews = newsService.findAllNews();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("newsDtoList",allNews);
        req.getRequestDispatcher(JspHelp.getPath("news")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
