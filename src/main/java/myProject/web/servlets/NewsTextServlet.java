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
import java.util.Optional;

@WebServlet("/newsText")
public class NewsTextServlet extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance();
    private static final NewsTextServlet INSTANCE = new NewsTextServlet();
    public static NewsTextServlet getInstance(){
        return INSTANCE;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<NewsDto> news = newsService.findById(req.getParameter("newsId") != null ? req.getParameter("newsId") :
                String.valueOf(req.getAttribute("newsId")));
        if(news.isPresent()){
            NewsDto newsDto = news.get();
            req.setAttribute("news",newsDto);
        }
        req.getRequestDispatcher(JspHelp.getPath("newsText")).forward(req,resp);
    }
}
