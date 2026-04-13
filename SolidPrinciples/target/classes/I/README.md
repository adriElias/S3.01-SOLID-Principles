# 🔌 I - Interface Segregation Principle (ISP)

## 🧠 What is it?

The **Interface Segregation Principle (ISP)** states that:

> **A class should not be forced to implement methods it does not need.**

This means that interfaces **must be specific and well-defined according to functionality**. If an interface is too large or generic, it can force classes to implement methods that don't make sense for them.

⚠️ This problem is known as **fat interfaces** or **"code smell"** of **bloated interfaces** with too many responsibilities.

`*` **"Code smell"** refers to a characteristic of source code that suggests a deeper problem or a **possible error in the design or structure of the code**.

## 🚨 Why is it important?

Having **smaller and more specific interfaces**, it's easier to **reuse** the same interfaces in different parts of the system **without causing compatibility issues
Having **smaller and more specific interfaces**, it's easier to **reuse** the same interfaces in different parts of the system **without causing compatibility issues.**
### 👩‍🏫 **Example:**

Suppose you are designing an application that works with **various types of printers** and offers an interface like this:
Suppose you are designing an application that works with **various types of printers** and offers an interface like this:

```java
public interface Printer {
    void print(String document);
    void scan(String document);
    void sendFax(String document);
}

public class BasicPrinter implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException("This printer does not scan.");
    }

    @Override
    public void sendFax(String document) {
        throw new UnsupportedOperationException("This printer does not send faxes.");
    }
}
```
🔴 The problem with this interface is that it groups **too many responsibilities in a single interface**. Not all printers have the ability to scan or send faxes, but they are still **forced to implement these methods**.

⚠️ This violates the **Interface Segregation Principle (ISP)**

✅ Solution with ISP:
> Divide the Printer interface into **smaller, more specific interfaces** (Printer, Scanner, Fax), and make **each class implement only the ones it needs**.

- **1️⃣ Segregated interfaces according to functionality:**

```java
public interface Printer {
    void print(String document);
}

public interface Scanner {
    void scan(String document);
}

public interface Fax {
    void sendFax(String document);
}
```

- **2️⃣ Basic printer: only prints:**

```java
public class BasicPrinter implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}
```

- **3️⃣ Printer with scanner: prints and scans:**

```java
public class ScannerPrinter implements Printer, Scanner {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }
}
```

- **4️⃣ Multifunction printer: prints, scans and sends faxes:**

```java
public class MultifunctionPrinter implements Printer, Scanner, Fax {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }

    @Override
    public void sendFax(String document) {
        System.out.println("Sending
    @Override
    public void sendFax(String document) {
        System.out.println("Sending fax: " + document);
    }
}
```
## 🎯 Exercise Objective

In the attached Java file you will find a class or class hierarchy that implements an **interface that is too large**.

🔧 Your challenge is:

1. Detect which methods **don't make sense** for some of the classes.
2. Refactor the interface into **smaller and more focused interfaces**.
3. Make each class implement **only the interfaces it needs**.
1. Detect which methods **don't make sense** for some of the classes.
2. Refactor the interface into **smaller and more focused interfaces**.
3. Make each class implement **only the interfaces it needs**.
## 📌 Tips for applying ISP

✅ **If a class has to implement a method that only throws an exception or is empty... you might be violating ISP.**

✅ **Prefer several specific interfaces over one single and generic one.**

✅ **Small and focused interfaces promote a more flexible and maintainable design.**
✅ **Prefer several specific interfaces over one single and generic one.**

✅ **Small and focused interfaces promote a more flexible and maintainable design.**

## 💬 Reflection

When **ISP** is followed:
- Classes are simpler and more coherent.
- We avoid absurd or unnecessary implementations.
- It facilitates the use of composition instead of forced inheritance.

🔁 **More modularity, less coupling.**

---

🚀 Let's go! Review the interface, apply the **ISP** principle and refactor it elegantly.

❓ **Does your interface do too much? Which parts could be divided?**

---

## ✅ Solution - Folder Structure

Here's the recommended folder structure after applying the ISP principle:

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
│   │       │   ├── README.md                 # This file with documentation
│   │       │   ├── _new/                     # Refactored solution
│   │       │   │   ├── MachineActions.java   # Interface: common machine actions (turnOn, turnOff)
│   │       │   │   ├── Heatable.java         # Interface: heating capability
│   │       │   │   ├── Coolable.java         # Interface: cooling capability
│   │       │   │   ├── Washable.java         # Interface: washing capability
│   │       │   │   ├── AirConditioner.java   # Implementation: heating and cooling machine
│   │       │   │   ├── WashingMachine.java   # Implementation: washing machine
│   │       │   │   └── Main.java             # Main class to test the solution
│   │       │   │
│   │       │   └── _old/                     # Original violation of ISP (for reference)
│   │       │       └── MachineActions.java   # Example with bloated interface
│   │       │
│   │       ├── L/                            # Liskov Substitution Principle examples
│   │       │   ├── README.md
│   │       │   ├── _new/                     # Refactored solution
│   │       │   └── _old/                     # Original violation
│   │       │
│   │       ├── O/                            # Open/Closed Principle examples
│   │       │   ├── README.md
│   │       │   ├── _new/                     # Refactored solution
│   │       │   └── _old/                     # Original violation
│   │       │
│   │       └── S/                            # Single Responsibility Principle examples
│   │           ├── README.md
│   │           ├── _new/                     # Refactored solution
│   │           └── _old/                     # Original violation
│   │
│   └── test/
│       └── java/
│           ├── D/                            # Tests for Dependency Inversion
│           ├── I/                            # Tests for Interface Segregation
│           │   ├── AirConditionerTest.java   # Unit tests for AirConditioner
│           │   └── WashingMachineTest.java   # Unit tests for WashingMachine
│           │
│           ├── L/                            # Tests for Liskov Substitution
│           │   └── CharacterTest.java        # Unit tests for characters
│           │
│           ├── O/                            # Tests for Open/Closed
│           │   └── InstrumentPlayerTest.java # Unit tests for instruments
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
| **MachineActions** | Interface defining basic machine operations (turnOn, turnOff) |
| **Heatable** | Interface defining heating capability |
| **Coolable** | Interface defining cooling capability |
| **Washable** | Interface defining washing capability |
| **AirConditioner** | Implementation: implements MachineActions, Heatable, Coolable |
| **WashingMachine** | Implementation: implements MachineActions, Washable |
| **Main** | Entry point and usage demonstration |

### Key Benefits

✅ **Segregated Interfaces** - Each interface has a single, well-defined responsibility
✅ **AirConditioner** - Only implements the interfaces it needs (MachineActions, Heatable, Coolable)
✅ **WashingMachine** - Only implements the interfaces it needs (MachineActions, Washable)
✅ **No Unused Methods** - Classes don't have to implement methods they don't use
✅ **Flexible Design** - New machine types can implement only the interfaces they require

Each interface follows **ISP**: specific to its purpose and reusable across different implementations ✨
❓ **Does your interface do too much? Which parts could be divided?**

---
