package myProject.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewsDto {
    private final Integer id;
    private final String name;
    private final String text;
    private final Integer authorId;
}
