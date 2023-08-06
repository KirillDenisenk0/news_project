package myProject.web.service;

import myProject.dto.CommentsDto;
import myProject.exeception.BanException;
import myProject.exeception.CensorshipException;
import myProject.mapper.CommentsDtoMapper;
import myProject.validator.NewPublicationValidationResult;
import myProject.validator.NewPublicationValidator;
import myProject.web.dao.CommentsDao;
import myProject.web.dao.UserDao;
import myProject.web.model.Comments;
import myProject.web.model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class CommentService {
    private static final CommentService INSTANCE = new CommentService();
    private final UserDao userDao = UserDao.getInstance();

    private final CommentsDao commentsDao = CommentsDao.getInstance();
    private final CommentsDtoMapper commentsDtoMapper = CommentsDtoMapper.getInstance();
    private final NewPublicationValidator newPublicationValidator = NewPublicationValidator.getInstance();

    public Comments create(CommentsDto commentsDto) throws SQLException {
        Optional<User> optionalUser = userDao.findById(commentsDto.getAuthorId());
        if(!checkUser(optionalUser)){
            throw new BanException("Ваш аккаунт заблокирован. Вы не можете публиковать новости оставлять комментарии ");
        }
        NewPublicationValidationResult newCommentsValidationResult = newPublicationValidator.isValidComment(commentsDto);
        if (newCommentsValidationResult.isValid()) {
            banAction(optionalUser);
        }
        Comments comments = commentsDtoMapper.mapToEntity(commentsDto);
        return commentsDao.create(comments);
    }

    private boolean checkUser(Optional<User> optionalUser){
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return !userDao.getIsBanned(user.getId()) || userDao.getTurnOffBanDate(user.getId()).isEqual(userDao.getBanDate(user.getId()).plusDays(1));
        }
        return true;
    }

    private void banAction(Optional<User> userOptional) {
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if (!userDao.getNotice(user.getId())) {
                userDao.setNotice(user.getId(),true);
                throw new CensorshipException("Вам вынесено предупреждение за ненормативную лексику. В следующий раз вы будете забанены");
            }
            else{
                userDao.setIsBanned(user.getId(), true);
                userDao.setBanDate(user.getId(), LocalDateTime.now());
                userDao.setTurnOffBanDate(user.getId(), LocalDateTime.now().plusHours(24));
                throw new BanException("Вы забанены за неоднократное нарушение правил портала");
            }
        }
    }
    public static CommentService getInstance(){
        return INSTANCE;
    }
}
