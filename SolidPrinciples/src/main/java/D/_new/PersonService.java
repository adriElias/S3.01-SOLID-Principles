package D._new;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public void savePerson(Person person){
        personRepository.save(person);        

    }
}
