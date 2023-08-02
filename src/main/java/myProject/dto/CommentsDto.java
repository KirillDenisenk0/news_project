package myProject.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentsDto {
    String text;
    String newsId;
    Integer authorId;
}
