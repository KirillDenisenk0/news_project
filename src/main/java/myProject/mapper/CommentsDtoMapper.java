package myProject.mapper;

import myProject.dto.CommentsDto;
import myProject.web.model.Comments;

public class CommentsDtoMapper implements Mapper<CommentsDto, Comments > {
    private static final CommentsDtoMapper INSTANCE = new CommentsDtoMapper();

    @Override
    public Comments mapToEntity(CommentsDto commentsDto) {
        return Comments.builder()
                .text(commentsDto.getText())
                .authorId(commentsDto.getAuthorId())
                .newsId(Integer.parseInt(commentsDto.getNewsId()))
                .build();
    }

    public static CommentsDtoMapper getInstance(){
        return INSTANCE;
    }

}
