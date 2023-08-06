package myProject.mapper;

import myProject.dto.NewsDto;
import myProject.web.model.News;

import java.time.LocalDate;
import java.util.Map;

public class NewsDtoMapper implements Mapper<NewsDto, News> {
    private static final NewsDtoMapper INSTANCE = new NewsDtoMapper();
    @Override
    public News mapToEntity(NewsDto newsDto) {
        return News.builder()
                .name(newsDto.getName())
                .createDate(LocalDate.now())
                .text(newsDto.getText())
                .authorId(newsDto.getAuthorId())
                .build();
    }

    public static NewsDtoMapper getInstance(){
        return INSTANCE;
    }
}
