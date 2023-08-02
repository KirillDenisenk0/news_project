package myProject.exeception;

public class BanException extends RuntimeException{
    private final String errorText;

    public BanException(String errorText){
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
