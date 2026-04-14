package D;

import D._new.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonServiceTest {
    
    private PersonService personService;
    private MockPersonRepository mockRepository;
    
    @Before
    public void setUp() {
        mockRepository = new MockPersonRepository();
        personService = new PersonService(mockRepository);
    }
    
    @Test
    public void testConstructorInitializesService() {
        assertNotNull(personService);
    }
    
    @Test
    public void testSavePersonCallsRepository() {
        Person person = new Person("Juan");
        personService.savePerson(person);
        
        assertTrue(mockRepository.wasPersonSaved());
        assertEquals("Juan", mockRepository.getLastSavedPersonName());
    }
    
    @Test
    public void testSaveMultiplePersons() {
        Person person1 = new Person("Carlos");
        Person person2 = new Person("Ana");
        Person person3 = new Person("Luis");
        
        personService.savePerson(person1);
        personService.savePerson(person2);
        personService.savePerson(person3);
        
        assertEquals(3, mockRepository.getSaveCount());
        assertEquals("Luis", mockRepository.getLastSavedPersonName());
    }
    
    @Test
    public void testSavePersonWithEmptyName() {
        Person person = new Person("");
        personService.savePerson(person);
        
        assertTrue(mockRepository.wasPersonSaved());
        assertEquals("", mockRepository.getLastSavedPersonName());
    }
    
    @Test
    public void testSavePersonPreservesPersonData() {
        Person person = new Person("Roberto");
        personService.savePerson(person);
        
        Person savedPerson = mockRepository.getLastSavedPerson();
        assertNotNull(savedPerson);
        assertEquals("Roberto", savedPerson.getName());
    }
    
    private static class MockPersonRepository implements PersonRepository {
        private Person lastSavedPerson;
        private int saveCount = 0;
        
        @Override
        public void save(Person person) {
            this.lastSavedPerson = person;
            this.saveCount++;
        }
        
        public boolean wasPersonSaved() {
            return lastSavedPerson != null;
        }
        
        public String getLastSavedPersonName() {
            return lastSavedPerson != null ? lastSavedPerson.getName() : null;
        }
        
        public Person getLastSavedPerson() {
            return lastSavedPerson;
        }
        
        public int getSaveCount() {
            return saveCount;
        }
    }
}
