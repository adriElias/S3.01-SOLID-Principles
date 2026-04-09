package L._new;

public class Warrior extends Character implements Damageable {
    @Override
    public void takeDamage(int points) {
        System.out.println("The warrior resists and only takes " + (points / 2) + " points of damage.");
    }
}
