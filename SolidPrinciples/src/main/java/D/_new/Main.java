package D._new;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Adriana");

        PersonService mysqlService = new PersonService(new MySqlPersonRepository());
        mysqlService.savePerson(person);


        PersonService postgreService = new PersonService(new PostgreSqlPersonRepository());
        postgreService.savePerson(person);
    
    }
}
