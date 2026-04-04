package S._new;

public class Main {
    public static void main(String[] args) {
        User user = new User("Adriana", "Adriana@example.com", "SecurePassword1");

        UserValidator validator = new UserValidator();
        EmailService emailService = new EmailService();
        UserRegistrationService userRegistrationService = new UserRegistrationService(validator, emailService);

        userRegistrationService.register(user);
    }
}