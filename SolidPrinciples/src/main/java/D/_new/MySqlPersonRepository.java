package D._new;

public class MySqlPersonRepository implements PersonRepository {
    
    @Override
    public void save(Person person){
        System.out.println("Save person in MySQL: " + person.getName());
    }
}
