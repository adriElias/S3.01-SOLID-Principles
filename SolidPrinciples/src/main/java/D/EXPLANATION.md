# Explanation: Dependency Inversion Principle

## What was wrong with ServicePerson.java?

When I looked at the old ServicePerson class, it had a savePerson method that created a new MySql object directly inside the method.

```java
public void savePerson(Person person) {
    MySql mysql = new MySql();
    mysql.savePerson(person);
}
```

The problem is that ServicePerson depends directly on the concrete MySql class. It's tightly coupled to MySQL. If I ever needed to switch to PostgreSQL or any other database, I would have to modify ServicePerson.

This violates the Dependency Inversion Principle. High-level modules like ServicePerson shouldn't depend on low-level modules like MySql. They should both depend on abstractions. Also, if ServicePerson needs to be tested, I can't easily swap MySql for a fake implementation because it's hardcoded in the method.

## How did I fix it?

I created an abstraction that both high and low-level modules can depend on:

**PersonRepository** - An interface defining what any data storage needs to do. Just one method: save(Person person).

**MySqlPersonRepository** - Implements PersonRepository and handles MySQL-specific saving.

**PostgreSqlPersonRepository** - Implements PersonRepository and handles PostgreSQL-specific saving.

**PersonService** - The high-level module (renamed from ServicePerson). Instead of creating MySql directly, it receives a PersonRepository through the constructor:

```java
public PersonService(PersonRepository personRepository){
    this.personRepository = personRepository;
}

public void savePerson(Person person){
    personRepository.save(person);
}
```

Now PersonService depends on the PersonRepository abstraction, not on concrete database classes.

## Why is this better?

Now if I need to switch from MySQL to PostgreSQL, I just create a PostgreSqlPersonRepository instance and pass it to PersonService. I don't have to touch PersonService code at all.

If I want to test PersonService, I can create a fake PersonRepository that stores data in memory instead of a real database. No modifications needed.

The code is flexible. If in the future I need to use a different database like MongoDB or Firebase, I just create a new implementation of PersonRepository. All the high-level code keeps working.

## Bottom line

High-level modules shouldn't depend on low-level modules. Both should depend on abstractions. And concrete implementations shouldn't be created inside methods - they should be injected from outside. This gives you flexibility and makes code much easier to test and maintain.
