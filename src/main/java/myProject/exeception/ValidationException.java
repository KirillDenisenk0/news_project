package myProject.exeception;

import lombok.Getter;
import myProject.validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errorList;
    public ValidationException(List<Error> errorList) {
        this.errorList = errorList;
    }
}
