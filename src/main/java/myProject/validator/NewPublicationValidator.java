package myProject.validator;

import myProject.dto.NewsDto;

public class NewPublicationValidator implements ValidatorForPublication<NewPublicationValidationResult> {

    @Override
    public NewPublicationValidationResult isValidNews(NewsDto newsDto) {
        return new NewPublicationValidationResult();
    }
}
