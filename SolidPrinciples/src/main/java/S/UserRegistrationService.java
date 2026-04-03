package S;

public class UserRegistrationService {

    private final UserValidator validator;
    private final EmailService emailService;

    public UserRegistrationService(UserValidator validator, EmailService emailService) {
        this.validator = validator;
        this.emailService = emailService;
    }

    public void register(User user) {
        validator.validate(user);

        emailService.sendConfirmationEmail(user.getEmail());

        boolean userConfirmed = true;
        if (!userConfirmed) {
            System.out.println("User did not confirm registration.");
            return;
        }

        System.out.println("User registered successfully: " + user.getName());
    }
}