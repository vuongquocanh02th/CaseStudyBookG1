package utils;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        // Implement email validation logic
        return email != null && email.contains("@");
    }
}