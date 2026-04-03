package S._old;

public class User_old {
    private String name;
    private String email;
    private String password;

    public User_old(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void register() {

        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        if (password == null || password.length() < 8 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain an uppercase letter.");
        }

        System.out.println("📧 Sending confirmation email to: " + email);

        boolean userConfirmed = true;
        if (!userConfirmed) {
            System.out.println("⚠️ User did not confirm registration.");
            return;
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}