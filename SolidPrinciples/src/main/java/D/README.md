# 🧲 D - Dependency Inversion Principle (DIP)

## 🧠 What is it?

The **Dependency Inversion Principle (DIP)** states that:

> **Classes should depend on abstractions, not on concrete classes.**

In other words, code should be **based on interfaces or abstract classes, not on specific implementations**. This allows different parts of the system to be **decoupled**, which **facilitates change, replacement, and reuse** of components.

📌 This can be summarized in two key rules:
- 1️⃣ **High-level modules** (business logic) **should not depend on low-level modules** (implementations).
- 2️⃣ **Both should depend on abstractions**.

## 🚨 Why is it important?
Without DIP, high-level components can become **tightly coupled to concrete implementations**, making the system **difficult to modify, test, or extend**.

When DIP is applied correctly:

- ✅ Code is more flexible and easier to test.
- ✅ Implementations can be replaced without affecting the main logic.
- ✅ It encourages dependency injection and abstraction-oriented programming.

### 👩‍🏫 **Example:**

Suppose you have an application that manages notifications and they are always sent by email:

```java
public class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}
```
```java
public class NotificationManager {
    private EmailService emailService;

    public NotificationManager() {
        this.emailService = new EmailService();
    }

    public void notify(String message) {
        emailService.sendEmail(message);
    }
}
```

🔴 Problem:
`NotificationManager` depends directly on `EmailService` (a concrete implementation). If you want to add other channels like SMS, WhatsApp, or Push, you would need to modify the class.

⚠️ This violates the **OCP** principle and also the **DIP**.

✅ Solution with DIP:

- **1️⃣ Create an abstraction (interface) for the notification service:**

```java
public interface NotificationChannel {
    void send(String message);
}

```
- **2️⃣ Make EmailService implement the interface:**

```java
public class EmailService implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
```
- **3️⃣ Modify NotificationManager to depend on the abstraction:**

```java
public class NotificationManager {
    private NotificationChannel channel;

    public NotificationManager(NotificationChannel channel) {
        this.channel = channel;
    }

    public void notify(String message) {
        channel.send(message);
    }
}

```
- **4️⃣ Now you can inject different channels without modifying NotificationManager:**

```java
public class Main {
    public static void main(String[] args) {
        NotificationChannel channel = new EmailService(); // or new SmsService(), new PushService()...
        NotificationManager manager = new NotificationManager(channel);
        manager.notify("Hello world!");
    }
}
```

---

## 🎯 Exercise Objective

In the attached Java files, you will find classes that **do not respect this principle**: they have direct dependencies on concrete implementations.

🔧 Your challenge is:

1. Analyze the direct dependencies between classes.
2. Create **abstractions (interfaces)** that decouple the classes.
3. Use **dependency injection** to provide implementations at runtime.
4. Ensure the code is flexible and easy to test.

---

## 📌 Tips for applying DIP

✅ Ask yourself: *"Does this class depend on concrete implementations?" and "Could I swap this implementation without changing the class?"*

✅ If the answer is no... it's time to create an abstraction!

✅ Use **interfaces** to define contracts between components.

✅ Inject dependencies through **constructors** or **setter methods**.

---

## 💬 Reflection

When classes depend on abstractions:
- It's easier to test (you can use mock implementations).
- It's easier to swap implementations (you can switch between MySQL and PostgreSQL without changing business logic).
- It's easier to extend functionality (you can add new implementations without modifying existing code).

🔁 **Lower coupling, higher modularity.**

---

🚀 Let's go! Review the code, apply the DIP principle, and enjoy the refactoring process.

❓ **How many concrete dependencies can you identify in the code?**

---

## ✅ Solution - Folder Structure

Here's the recommended folder structure after applying the DIP principle:

```
SolidPrinciples/
├── pom.xml                                    # Maven configuration file
├── README.md                                  # Project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── D/                            # Dependency Inversion Principle examples
│   │       │   ├── README.md                 # This file with documentation
│   │       │   ├── _new/                     # Refactored solution applying DIP
│   │       │   │   ├── Person.java           # Simple data class
│   │       │   │   ├── PersonRepository.java # ABSTRACTION: Interface for data persistence
│   │       │   │   ├── MySqlPersonRepository.java    # CONCRETE: MySQL implementation
│   │       │   │   ├── PostgreSqlPersonRepository.java # CONCRETE: PostgreSQL implementation
│   │       │   │   ├── PersonService.java    # Depends on abstraction (PersonRepository)
│   │       │   │   └── Main.java             # Shows dependency injection in action
│   │       │   │
│   │       │   └── _old/                     # Original violation of DIP (for reference)
│   │       │       ├── MySQL.java            # CONCRETE: Direct MySQL dependency
│   │       │       ├── Person.java           # Data class
│   │       │       └── ServicePerson.java    # VIOLATES DIP: Depends on concrete implementations
│   │       │
│   │       ├── I/                            # Interface Segregation Principle examples
│   │       ├── L/                            # Liskov Substitution Principle examples
│   │       ├── O/                            # Open/Closed Principle examples
│   │       └── S/                            # Single Responsibility Principle examples
│   │
│   └── test/
│       └── java/
│           ├── D/                            # Tests for Dependency Inversion
│           │   ├── PersonTest.java           # Tests for Person class
│           │   └── PersonServiceTest.java    # Tests for PersonService with mocked repository
│           │
│           ├── I/                            # Tests for Interface Segregation
│           ├── L/                            # Tests for Liskov Substitution
│           ├── O/                            # Tests for Open/Closed
│           └── S/                            # Tests for Single Responsibility
│
└── target/                                    # Maven build output (generated)
    ├── classes/                              # Compiled main classes
    └── test-classes/                         # Compiled test classes
```

### Description of each class

| Class | Responsibility | Type |
| --- | --- | --- |
| `Person` | Store person data (name) | Data Class |
| `PersonRepository` | Define the contract for data persistence | **ABSTRACTION (Interface)** |
| `MySqlPersonRepository` | Implement person persistence using MySQL | Concrete Implementation |
| `PostgreSqlPersonRepository` | Implement person persistence using PostgreSQL | Concrete Implementation |
| `PersonService` | Manage person business logic (depends on abstraction) | Service |
| `Main` | Show dependency injection in action | Entry Point |

### Key Improvements

✅ **Abstraction First**: `PersonService` now depends on `PersonRepository` (interface), not on concrete implementations.

✅ **Dependency Injection**: The repository is injected via the constructor, making it easy to swap implementations.

✅ **Testability**: Can easily create mock implementations of `PersonRepository` for testing.

✅ **Scalability**: Adding new repository implementations (e.g., MongoDB, Oracle) requires no changes to `PersonService`.
