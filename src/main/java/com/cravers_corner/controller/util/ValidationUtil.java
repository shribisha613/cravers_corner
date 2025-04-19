package com.cravers_corner.controller.util;

import java.util.regex.Pattern;

import javax.servlet.http.Part;


public class ValidationUtil {

    // 1. Check if a string is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidName(String value) {
        return value != null && value.matches("^[A-Za-z\\s]{2,}$");
    }
    
    // 3. Validate username: starts with a letter, allows letters, numbers, underscores, minimum 7 characters
    public static boolean isValidUsername(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9_]{6,}$");
    }

    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }
    // 4. Validate phone: exactly 10 digits
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^\\d{10}$");
    }

    // 5. Validate address: minimum 5 characters (letters, numbers, basic symbols)
    public static boolean isValidAddress(String address) {
        return address != null && address.matches("^[a-zA-Z0-9 ,.-]{5,}$");
    }

    // 6. Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@([\\w-]+\\.)+[a-zA-Z]{2,6}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // 7. Validate password: at least 8 characters, one uppercase, one number, one special character
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    // 8. Check if password and confirm password match
    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    // 9. Validate uploaded image file extension
    public static boolean isValidImageExtension(Part imagePart) {
        if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
            return false;
        }
        String fileName = imagePart.getSubmittedFileName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }

}