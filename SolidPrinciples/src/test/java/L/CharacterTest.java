package L;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import L._new.Character;
import L._new.*;


public class CharacterTest{

    private Ghost ghost;
    private Warrior warrior;
    
    @Before
    public void setUp() {
        ghost = new Ghost();
        warrior = new Warrior();
    }
    
    @Test
    public void testGhostIsCharacter() {
        assertNotNull("The ghost should not be null", ghost);
        assertTrue("Ghost should be an instance of Character", ghost instanceof Character);
    }
    
    @Test
    public void testWarriorIsCharacter() {
        assertNotNull("The warrior should not be null", warrior);
        assertTrue("Warrior should be an instance of Character", warrior instanceof Character);
    }
    
    @Test
    public void testWarriorIsDamageable() {
        assertNotNull("The warrior should not be null", warrior);
        assertTrue("Warrior should implement Damageable", warrior instanceof Damageable);
    }
    
    @Test
    public void testGhostAttack() {
        assertNotNull("Ghost should have attack method", ghost);
        ghost.attack();
    }
    
    @Test
    public void testWarriorAttack() {
        assertNotNull("Warrior should have attack method", warrior);
        warrior.attack();
    }
    
    @Test
    public void testWarriorTakeDamage() {
        assertNotNull("The warrior should not be null", warrior);
        assertTrue("Warrior should implement Damageable", warrior instanceof Damageable);
        warrior.takeDamage(100);
    }
    
    @Test
    public void testLiskovSubstitutionPrinciple() {
        Character[] characters = new Character[2];
        characters[0] = ghost;
        characters[1] = warrior;
        
        for (Character character : characters) {
            assertNotNull("Character should not be null", character);
            character.attack();
        }
    }
    
    @Test
    public void testWarriorDamageCalculation() {
        assertNotNull("The warrior should not be null", warrior);
        warrior.takeDamage(100);
        warrior.takeDamage(200);
        warrior.takeDamage(10);
    }
}
