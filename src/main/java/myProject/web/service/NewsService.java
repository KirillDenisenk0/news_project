package myProject.web.service;

import myProject.dto.NewsDto;
import myProject.web.dao.NewsDao;
import myProject.web.dao.UserDao;
import myProject.web.model.News;
import myProject.web.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NewsService{
    private static final NewsService INSTANCE = new NewsService();
    private final UserDao userDao = UserDao.getInstance();
    private final NewsDao newsDao = NewsDao.getInstance();
    public List<NewsDto> findAllNews() throws SQLException {
        List<News> newsList = newsDao.findAll();
        return newsList.stream().map(news ->
                NewsDto.builder()
                        .name(news.getName())
                        .text(news.getText())
                        .id(news.getId())
                        .build()).collect(Collectors.toList());
    }

    public Optional<NewsDto> findById(String id){
        return newsDao.findById(id).map(news -> NewsDto.builder()
                .id(news.getId())
                .name(news.getName())
                .text(news.getText())
                .build());
    }

    public void create(NewsDto newsDto){
        Optional<User> user = userDao.findById(newsDto.getAuthorId());
        checkUser(user){
            // если бан, то новость создать нельзя, выкидываем какой нибудь exception
        }
        // валидация новости
        // два отдельных метода для валидации новости и комментария
    }

    public static NewsService getInstance(){
        return INSTANCE;
    }
}
