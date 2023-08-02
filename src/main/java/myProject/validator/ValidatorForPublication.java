package myProject.validator;

import myProject.dto.CommentsDto;
import myProject.dto.NewsDto;

import java.io.IOException;

public interface ValidatorForPublication<R> {
    R isValidComment(CommentsDto commentsDto);

    R isValidNews(NewsDto newsDto) throws IOException;
}
