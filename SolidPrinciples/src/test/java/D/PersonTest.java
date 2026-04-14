package D;

import D._new.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {
    
    private Person person;
    
    @Before
    public void setUp() {
        person = new Person("Juan");
    }
    
    @Test
    public void testConstructorInitializesPerson() {
        Person p = new Person("Carlos");
        assertEquals("Carlos", p.getName());
    }
    
    @Test
    public void testConstructorWithEmptyString() {
        Person p = new Person("");
        assertEquals("", p.getName());
    }
    
    @Test
    public void testGetName() {
        assertEquals("Juan", person.getName());
    }
    
    @Test
    public void testSetName() {
        person.setName("Diego");
        assertEquals("Diego", person.getName());
    }
    
    @Test
    public void testSetNameWithEmptyString() {
        person.setName("");
        assertEquals("", person.getName());
    }
    
    @Test
    public void testSetNameMultipleTimes() {
        person.setName("Carlos");
        assertEquals("Carlos", person.getName());
        
        person.setName("Roberto");
        assertEquals("Roberto", person.getName());
        
        person.setName("Miguel");
        assertEquals("Miguel", person.getName());
    }
    
    @Test
    public void testPersonCreationWithDifferentNames() {
        Person p1 = new Person("Ana");
        Person p2 = new Person("Laura");
        
        assertEquals("Ana", p1.getName());
        assertEquals("Laura", p2.getName());
        assertNotEquals(p1.getName(), p2.getName());
    }
}
