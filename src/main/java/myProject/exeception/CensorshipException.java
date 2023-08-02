package myProject.exeception;

public class CensorshipException extends RuntimeException {
    private final String errorText;

    public CensorshipException(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
