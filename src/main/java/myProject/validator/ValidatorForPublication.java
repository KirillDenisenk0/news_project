package myProject.validator;

import myProject.dto.NewsDto;

public interface ValidatorForPublication<R> {
    //R isValidComment(CommentsDto commentsDto);

    R isValidNews(NewsDto newsDto);
}
