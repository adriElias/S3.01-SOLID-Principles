# 🧱 S - Single Responsibility Principle (SRP)

## 🧠 What is it?

The **Single Responsibility Principle** states that:

> **A class should have only one reason to change.**

In other words, a class should have **only one responsibility**, or **only one reason to be modified**.

👩‍🏫 **Example:**
If you have a `Report` class that:
- generates content,
- prints the report,
- and saves the report.

```java
public class Informe {
    private String contingut;

    public Informe(String contingut) {
        this.contingut = contingut;
    }
    
    public String obtenirContingut() {
        return contingut;
    }

    public void imprimir() {
        System.out.println("Imprimint informe:");
        System.out.println(contingut);
    }

    public void desar(String nomFitxer) {
        try (FileWriter writer = new FileWriter(nomFitxer)) {
            writer.write(contingut);
            System.out.println("Informe desat a " + nomFitxer);
        } catch (IOException e) {
            System.err.println("Error en desar l'informe: " + e.getMessage());
        }
    }
}
```
🔴 Problema: Cada una d’aquestes funcions **pertany a responsabilitats diferents**, i haurien d’estar separades en diferents classes.

⚠️ Estàs violant el principi! 

✅ Versió refactoritzada amb SRP aplicat: separem les responsabilitats en classes diferents:

- **1️⃣ Report: contains only the content.**

```java
// Class with a single responsibility: maintain the content
public class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
```

- **2️⃣ Printer: responsible for printing.**

```java
// Class with a single responsibility: print reports
public class Printer {
    public void printReport(Report report) {
        System.out.println("Printing report:");
        System.out.println(report.getContent());
    }
}
```
- **3️⃣ Storage: responsible for saving the report.**

```java
// Class with a single responsibility: save reports
public class Storage {
    public void saveReport(Report report, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(report.getContent());
            System.out.println("Report saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }
}
```
- **4️⃣ Usage example:**

```java
public class Main {
    public static void main(String[] args) {
        Report report = new Report("This is the content of the report.");

        Printer printer = new Printer();
        printer.printReport(report);

        Storage storage = new Storage();
        storage.saveReport(report, "report.txt");
    }
}
```
---

## 🎯 Exercise Objective

In the attached Java file, you will find a class that **does not respect this principle**: it does too many things at once.

🔧 Your challenge is:

1. Analyze the multiple responsibilities that the class has.
2. Separate them into **different classes**, each with a single clear responsibility.
3. Keep the code readable, modular, and easy to maintain.

---

## 📌 Tips for applying SRP

✅ Ask yourself: *"What reasons would this class have to change?" and "What are the responsibilities of this class?"*

✅ If there's more than one... it's time to separate responsibilities!

✅ Don't be afraid to create **more small and focused classes**.

---


## 💬 Reflection

When a class has only one responsibility:
- It's easier to read.
- It's easier to test.
- It's less likely to generate errors when you change functionality.

🔁 **Less coupling, more cohesion.**

---

🚀 Let's go! Review the code, apply the SRP principle, and enjoy the refactoring process.

❓ **How many responsibilities does the class have?**

---

## ✅ Solution - Folder Structure

Here's the recommended folder structure after applying the SRP principle:

```
SolidPrinciples/
├── pom.xml                                    # Maven configuration file
├── README.md                                  # Project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── D/                            # Dependency Inversion Principle examples
│   │       ├── I/                            # Interface Segregation Principle examples
│   │       ├── L/                            # Liskov Substitution Principle examples
│   │       ├── O/                            # Open/Closed Principle examples
│   │       │   ├── README.md
│   │       │   ├── _new/                     # Refactored solution
│   │       │   └── _old/                     # Original violation
│   │       │
│   │       └── S/                            # Single Responsibility Principle examples
│   │           ├── README.md                 # This file with documentation
│   │           ├── _new/                     # Refactored solution applying SRP
│   │           │   ├── User.java             # Single responsibility: User data
│   │           │   ├── UserValidator.java    # Single responsibility: Email and password validation
│   │           │   ├── UserRegistrationService.java # Single responsibility: User registration logic
│   │           │   ├── EmailService.java     # Single responsibility: Email operations
│   │           │   └── Main.java             # Main class to test the solution
│   │           │
│   │           └── _old/                     # Original violation of SRP (for reference)
│   │               └── User_old.java         # Example of violating SRP
│   │
│   └── test/
│       └── java/
│           ├── D/                            # Tests for Dependency Inversion
│           ├── I/                            # Tests for Interface Segregation
│           ├── L/                            # Tests for Liskov Substitution
│           ├── O/                            # Tests for Open/Closed
│           │
│           └── S/                            # Tests for Single Responsibility
│               └── UserTest.java             # Unit tests for User and related classes
│
└── target/                                    # Maven build output (generated)
    ├── classes/                              # Compiled main classes
    └── test-classes/                         # Compiled test classes
```

### Description of each class

| Class | Responsibility |
| --- | --- |
| **User** | Stores user information (name, email, password) |
| **UserValidator** | Validates email format and password strength |
| **UserRegistrationService** | Orchestrates the user registration process |
| **EmailService** | Handles sending emails to users |
| **Main** | Entry point and usage demonstration |

### Key Benefits

✅ **User** - Only manages user data
✅ **UserValidator** - Only validates user information  
✅ **UserRegistrationService** - Only handles registration workflow
✅ **EmailService** - Only manages email communication

Each class has **one reason to change** and **one responsibility** ✨
