package myProject.validator;

import lombok.SneakyThrows;
import myProject.dto.CommentsDto;
import myProject.dto.NewsDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NewPublicationValidator implements ValidatorForPublication<NewPublicationValidationResult> {
    private static final NewPublicationValidator INSTANCE = new NewPublicationValidator();
    private static List<String> badWords;
    private static final String ABSOLUTE_PATH = "C:\\Users\\Bulbazaur1\\IdeaProjects\\myProjectNewss\\src\\main\\resources\\нецензурные слова.txt";

    static {
        try {
            badWords = Files.readAllLines(Path.of(ABSOLUTE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NewPublicationValidationResult isValidNews(NewsDto newsDto) {
        NewPublicationValidationResult newPublicationValidationResult = new NewPublicationValidationResult();
        for (String line : badWords) {
            if (newsDto.getText().contains(line)) {
                newPublicationValidationResult.setViolation(true);
                break;
            }
        }
        return newPublicationValidationResult;
    }

    @Override
    public NewPublicationValidationResult isValidComment(CommentsDto commentsDto) {
        NewPublicationValidationResult newPublicationValidationResult = new NewPublicationValidationResult();
        for (String line : badWords) {
            if (commentsDto.getText().contains(line)) {
                newPublicationValidationResult.setViolation(true);
                return newPublicationValidationResult;
            }
        }
        return newPublicationValidationResult;
    }
    public static NewPublicationValidator getInstance () {
        return INSTANCE;
    }
}
