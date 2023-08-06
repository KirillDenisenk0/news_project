package myProject.web.service;

import myProject.dto.NewsDto;
import myProject.exeception.BanException;
import myProject.exeception.CensorshipException;
import myProject.mapper.NewsDtoMapper;
import myProject.validator.NewPublicationValidationResult;
import myProject.validator.NewPublicationValidator;
import myProject.web.dao.NewsDao;
import myProject.web.dao.UserDao;
import myProject.web.model.News;
import myProject.web.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NewsService {
    private static final NewsService INSTANCE = new NewsService();
    private final UserDao userDao = UserDao.getInstance();
    private final NewsDao newsDao = NewsDao.getInstance();
    private final NewPublicationValidator newPublicationValidator = NewPublicationValidator.getInstance();
    private final NewsDtoMapper newsDtoMapper = NewsDtoMapper.getInstance();

    public List<NewsDto> findAllNews() throws SQLException {
        List<News> newsList = newsDao.findAll();
        return newsList.stream().map(news ->
                NewsDto.builder()
                        .name(news.getName())
                        .text(news.getText())
                        .id(news.getId())
                        .build()).collect(Collectors.toList());
    }

    public Optional<NewsDto> findById(String id) {
        return newsDao.findById(id).map(news -> NewsDto.builder()
                .id(news.getId())
                .name(news.getName())
                .text(news.getText())
                .build());
    }

    public void create(NewsDto newsDto) throws SQLException {
        Optional<User> user = userDao.findById(newsDto.getAuthorId());
        if (!checkUser(user)) {
            // если бан, то новость создать нельзя, выкидываем какой нибудь exception
            throw new BanException("Ваш аккаунт заблокирован. Вы не можете публиковать новости и оставлять комментарии");
        }
        NewPublicationValidationResult newPublicationValidationResult = newPublicationValidator.isValidNews(newsDto);
        if (newPublicationValidationResult.isValid()) {
            banAction(user, newsDto);
        }
        // валидация новости
        // два отдельных метода для валидации новости и комментария
        //смапить дто в сущность News и сохранить
        News news = newsDtoMapper.mapToEntity(newsDto);
        newsDao.create(news);
    }

    private void banAction(Optional<User> maybeUser, NewsDto newsDto) {
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            if (!userDao.getNotice(user.getId())) {
                userDao.setNotice(user.getId(), true);
                throw new CensorshipException("Вам вынесено предупреждение за ненормативную лексику. В следующий раз вы будете забанены");
            } else {
                userDao.setIsBanned(user.getId(), true);
                userDao.setBanDate(user.getId(), LocalDateTime.now());
            }
        }
    }


    {

    }

    // проверяем, что юзер не забанен
    private boolean checkUser(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return !userDao.getIsBanned(user.getId()) || userDao.getTurnOffBanDate(user.getId()).isEqual(userDao.getBanDate(user.getId()).plusDays(1));
        }
        return true;
    }

    public static NewsService getInstance() {
        return INSTANCE;
    }
}
