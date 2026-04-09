package L._new;

public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        Ghost ghost = new Ghost();
       
        warrior.attack();
        ghost.attack();
        warrior.takeDamage(40);

        System.out.println(ghost);
        System.out.println(warrior);
    }
}
