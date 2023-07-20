package co.com.auth_back.auth_back.utils;

public class StringUtil {
    public static String getFromTemplate(String template, String... args) {
        return String.format(template, args);
    }
}
