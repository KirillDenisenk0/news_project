package myProject.exeception;

public class DuplicateException extends RuntimeException{
    private final String ERROR_TEXT = "Sorry, this email or password already exists";

    public String getERROR_TEXT() {
        return ERROR_TEXT;
    }
}
