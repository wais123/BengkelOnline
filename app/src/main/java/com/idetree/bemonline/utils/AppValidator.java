package com.idetree.bemonline.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppValidator {
    public static boolean isValidPassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }else{
            return false;
        }
    }
    public static boolean isValidEmail(String email) {
        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        CharSequence inputString = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
