package myProject.web.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class News {
    private Long id;
    private String name;
    private LocalDate createDate;
    private String text;
    private Long authorId;
}
