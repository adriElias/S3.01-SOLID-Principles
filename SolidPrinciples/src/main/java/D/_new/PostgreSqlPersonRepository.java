package D._new;

public class PostgreSqlPersonRepository implements PersonRepository{

    @Override
    public void save(Person person){
        System.out.println("Save person in PostgreSql: " + person.getName());
    }
}
