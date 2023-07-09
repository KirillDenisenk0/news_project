package myProject.web.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelp {
    private static final String PATH = "/WEB-INF/%s.jsp";

    public static String getPath(String name){
        return String.format(PATH,name);
    }
}
