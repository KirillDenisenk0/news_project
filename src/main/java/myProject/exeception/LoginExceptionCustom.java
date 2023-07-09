package myProject.exeception;


public class LoginExceptionCustom extends Throwable {
    private final String errorText;

    public LoginExceptionCustom(String errorText){
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
