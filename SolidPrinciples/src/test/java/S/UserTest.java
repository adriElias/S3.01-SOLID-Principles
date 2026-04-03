package S;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UserTest {
    
    private User user;
    
    @Before
    public void setUp() {
        user = new User("Adriana", "adriana@example.com", "SecurePassword1");
    }
    
    @Test
    public void testUserCreation() {
        assertNotNull("The user should not be null", user);
        assertEquals("The name should be 'Adriana'", "Adriana", user.getName());
        assertEquals("The email should be 'adriana@example.com'", "adriana@example.com", user.getEmail());
        assertEquals("The password should be 'SecurePassword1'", "SecurePassword1", user.getPassword());
    }
    
    @Test
    public void testValidEmail() {
        User validUser = new User("Adriana", "adriana@example.com", "SecurePassword1");
        assertNotNull("The user with valid email should be created", validUser);
        assertEquals("adriana@example.com", validUser.getEmail());
    }
    
    @Test
    public void testUserPassword() {
        assertEquals("The password should be 'SecurePassword1'", "SecurePassword1", user.getPassword());
    }
}
