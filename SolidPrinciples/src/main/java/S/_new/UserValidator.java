package S._new;

public class UserValidator {

    public void validate(User user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException(
                "Password must be at least 8 characters long and contain an uppercase letter."
            );
        }
    }
}